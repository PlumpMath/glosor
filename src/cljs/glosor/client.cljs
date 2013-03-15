(ns client)

; (defn handle-click []
;   (js/alert "Hello!"))

; (def clickable (.getElementById js/document "clickable"))
; (.addEventListener clickable "click" handle-click)

(def languages [:isl :sve])
(def other-language {:isl :sve :sve :isl})

(def word (.getElementById js/document "word"))
(def answer (.getElementById js/document "answer"))
(def cheat (.getElementById js/document "cheat"))
(def langlabel (.getElementById js/document "langlabel"))

(def submit (.getElementById js/document "submit-button"))
(def hint (.getElementById js/document "hint-button"))

(def current-item (atom nil))
(def current-lang (atom nil))

(def wordlist [
	{:isl "hestur" :sve "häst"}
	{:isl "fjall" :sve "fjäll"}
	{:isl "eldhús" :sve "kök"}])

(defn show-new-word! []
	(let [item (rand-nth wordlist)
		  question-language (rand-nth languages) 
		  answer-language (other-language question-language)]
		(reset! current-item item)
		(reset! current-lang answer-language)
		(set! word.innerHTML (question-language item))
		(set! word.style.color "#000")
		(set! langlabel.innerHTML (name question-language))
		(set! cheat.style.display "none")
		(set! cheat.innerHTML (str "Answer: " (answer-language item)))))

(defn on-new-word-timeout []
	(show-new-word!))

(defn on-hint []
	(set! cheat.style.display "block"))

(defn on-correct []
	(set! word.style.color "#292")
	(js/setTimeout on-new-word-timeout 500))

(defn on-wrong []
	(set! word.style.color "#F00")
	(on-hint)
	(js/setTimeout on-new-word-timeout 1000))

(defn on-submit []
	;(js/console.log current-lang)
	;(js/console.log current-item)
	(let [correct (get @current-item @current-lang)]
		((if (= correct answer.value)
			on-correct
			on-wrong
			))))

(defn onkeypress [e]
	;(js/console.log (.-keyCode e))
	)

(.addEventListener submit "click" on-submit)
(.addEventListener hint "click" on-hint)

(show-new-word!)