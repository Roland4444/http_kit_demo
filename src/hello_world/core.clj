(ns hello-world.core
 (:require [org.httpkit.server :as server]
           [clostache.parser :as mustache])
 (:use [compojure.route :only [files not-found]]
       [compojure.core :only [defroutes GET POST DELETE ANY context]]  ;;https://http-kit.github.io/server.html#routing
       org.httpkit.server))

(comment
(ns ring.core
  (:use ring.adapter.jetty))
)

(defn mustache_render_plain [request]
  {:status 200
   :headers {"Content-Type" "text/plain"}
   :body (mustache/render "Hello, {{name}}!" {:name "Felix"})})


 (defn mustache_render_template [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (mustache/render-resource "templates/home.mustache"
                                {:name "roman"})})  ;;https://otee.dev/2022/01/25/clojure-backend-using-ring-jetty-compojure.html


(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))   


(defn chat-handler [request]
  (with-channel request channel
    (on-close channel (fn [status] (println "client close it" status)))
    (on-receive channel (fn [data] ;; echo it back
                          (send! channel data)))))

(defroutes all-routes
  (GET "/" [] mustache_render_plain)
  (GET "/template" [] mustache_render_template)
  (GET "/ws" [] chat-handler)     ;; websocket

  (files "/static/") ;; static file url prefix /static, in `public` folder
  (not-found "<p>Page not found.</p>")) ;; all other, return 404

(run-server all-routes {:port 8080})



(defn -main [& args]
  (foo "HI!")
  (run-server all-routes {:port 7500}))