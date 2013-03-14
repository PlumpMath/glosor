(ns hello-clojurescript)

; (defn handle-click []
;   (js/alert "Hello!"))

; (def clickable (.getElementById js/document "clickable"))
; (.addEventListener clickable "click" handle-click)

(def word (.getElementById js/document "word"))

(def wordlist ["hestur","fjall","og","eldhús"])

(set! word.innerHTML (rand-nth wordlist))