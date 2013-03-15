(ns client
  (:require [words :as w]
            [clojure.string :as string]))

(def languages [:isl :sve])
(def other-language {:isl :sve :sve :isl})

(def word (.getElementById js/document "word"))
(def correction (.getElementById js/document "correction"))
(def answer (.getElementById js/document "answer"))
(def cheat (.getElementById js/document "cheat"))
(def langlabel (.getElementById js/document "langlabel"))
(def submit (.getElementById js/document "submit-button"))

(def state (atom {:all-items w/all-words
                  :item nil
                  :question-language nil
                  :answer-language nil}))

(defn get-new-word []
  (rand-nth (:all-items @state)))

(defn show-new-word! []
  (let [item (get-new-word)
        question-language (rand-nth languages) 
        answer-language (other-language question-language)]
    (swap! state merge {:item item
                        :question-language question-language
                        :answer-language answer-language})
    (set! word.innerHTML (question-language item))
    (set! word.style.color "#000")
    (set! correction.innerHTML "")
    (set! langlabel.innerHTML (str "(" (name question-language) " " (string/join " " (map #(name %) (:tags item))) ")"))
    (set! answer.value "")
    (.focus answer)))

(defn correct-answer []
  (get (:item @state) (:answer-language @state)))

(defn on-new-word-timeout []
  (show-new-word!))

(defn on-correct []
  (set! word.style.color "#292")
  (js/setTimeout on-new-word-timeout 500))

(defn on-wrong []
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

(.addEventListener submit "click" on-submit)

(show-new-word!)