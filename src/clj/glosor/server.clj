(ns glosor.server
  (:use compojure.core
        [hiccup.middleware :only (wrap-base-url)])
  (:require [ring.adapter.jetty :as jetty]
            [ring.util.response :as rur]
            [compojure.route :as route]
            [compojure.handler :as handler]
            [compojure.response :as response]))

(defroutes main-routes
  (GET "/" [] (rur/file-response "index.html" {:root "./resources/public"}))
  (route/resources "/")
  (route/not-found "Page not found"))

(def app
  (-> (handler/site main-routes)
      (wrap-base-url)))

(defn -main [& args]
  (let [port (if (empty? args) 3000 (Integer. (first args)))]
    (jetty/run-jetty app {:port port})))