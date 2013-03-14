(ns glosor.server
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.resource :as resources]
            [ring.util.response :as response]
            [hiccup.core :as h]
            [hiccup.page :as hp]
            [hiccup.form :as hf])
  (:gen-class))

(def wordlist ["häst" 
               "hund"
               "katt"
               "fisk"])

(defn app-body [] 
  (h/html 
   (hp/include-css "css/bootstrap.min.css")
   [:content [:p {:id "word"} "empty"]
              (hf/text-field "answer" "Answer here")
              (hf/submit-button "Submit")]
   (hp/include-js "js/cljs.js")
   (hp/include-js "js/bootstrap.min.js")))

(defn response-404 []
  (h/html
   [:h1 404]))

(defn render-app []
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (app-body)})

(defn render-wordlist []
  (println "fetching wordlist...")
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (binding [*print-dup* false] (str wordlist))})

(defn handler [request]
  (if (= "/wordlist" (:uri request))
    (render-wordlist)
    (response-404)))

(def app 
  (-> handler
      (resources/wrap-resource "public")))

(defn -main [& args]
  (jetty/run-jetty app {:port 3000}))

;(-main)
