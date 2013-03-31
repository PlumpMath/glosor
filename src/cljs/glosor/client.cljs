(ns client
  (:require [words :as w]
            [clojure.string :as string]))

(def languages [:isl :sve])
(def other-language {:isl :sve :sve :isl})

(def word (.getElementById js/document "word"))
(def correction (.getElementById js/document "correction"))
(def answer (.getElementById js/document "answer"))
(def cheat (.getElementById js/document "cheat"))
(def score (.getElementById js/document "score"))
(def langlabel (.getElementById js/document "langlabel"))
(def submit (.getElementById js/document "submit-button"))
(def ask-both (.getElementById js/document "ask-both"))
(def ask-isl (.getElementById js/document "ask-isl"))
(def ask-sve (.getElementById js/document "ask-sve"))

(def state (atom {:all-items (w/reset w/all-items)
                  :item nil
                  :question-language nil
                  :answer-language nil}))

(defn log-state []
  (js/console.log "State: " (str @state)))

(defn get-hard-items [percentage]
  "Returns the items with the lowest score. 
  Percentage is a value between 0.0 (only first word) and 1.0 (all words)"
  (let [items (:all-items @state)
        n (int (* percentage (count items)))
        sorted (sort #(< (:score %1) (:score %2)) items)]
    (subvec sorted 0 n)))

(defn get-new-item []
  (rand-nth (get-hard-items 0.4)))

(defn get-language []
  (cond 
   (.hasClass (js/jQuery ask-isl) "active") :isl
   (.hasClass (js/jQuery ask-sve) "active") :sve
   :else (rand-nth languages)))

(defn tags-as-string [question-language item]
  (let [filtered-tags (remove #{:subs :mask :fem :neutr} (:tags item))]
    (str "(" (name question-language) " " (string/join " " (map #(name %) filtered-tags)) ")")))

(defn show-new-word! []
  (let [item (get-new-item)
        question-language (get-language)
        answer-language (other-language question-language)]
    (js/console.log (get-language))
    (swap! state merge {:item item
                        :question-language question-language
                        :answer-language answer-language})
    (set! word.innerHTML (question-language item))
    (set! word.style.color "#000")
    (set! correction.innerHTML "")
    (set! langlabel.innerHTML (tags-as-string question-language item))
    (set! answer.value "")
    ;(set! score.innerHTML (str (map #(str (:isl %) ": " (:score %)) (:all-items @state))))
    (.focus answer)))

(defn correct-answer []
  (get (:item @state) (:answer-language @state)))

(defn on-new-word-timeout []
  (show-new-word!))

(defn change-score [updating-function]
  (let [item-index (get-in @state [:item :index])]
    (swap! state update-in [:all-items item-index :score] updating-function)))  

(defn on-correct []
  (change-score inc)
  (set! word.style.color "#292")
  (js/setTimeout on-new-word-timeout 500))

(defn on-wrong []
  (change-score dec)
  (set! word.style.color "#F00")
  (set! correction.innerHTML (str "= " (correct-answer)))
  (js/setTimeout on-new-word-timeout 1000))

(defn on-submit []
  ((if (= (correct-answer) answer.value)
     on-correct
     on-wrong)))

(defn onkeydown [event]
  (when (= 13 (.-keyCode event))
    (on-submit)))

(defn on-select-lang [event]
  (js/setTimeout show-new-word! 10)) ; A (terrible) hack to work around that the "click" event happens before the button is set to selected

(.addEventListener submit "click" on-submit)
(.addEventListener ask-both "click" on-select-lang)
(.addEventListener ask-isl "click" on-select-lang)
(.addEventListener ask-sve "click" on-select-lang)

(.button (js/jQuery ask-both) "toggle")
(show-new-word!)