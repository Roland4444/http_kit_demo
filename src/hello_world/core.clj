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

(defn mustache_render_ws_template [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (mustache/render-resource "templates/ws.html"
                                {:name "roman"})})  ;


(defn mustache_render_template_with_static [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (mustache/render-resource "templates/img.html"
                                {:name "roman"})})  ;


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
  (GET "/img" [] mustache_render_template_with_static)

  (GET "/ws" [] chat-handler)     ;; websocket

  (files "/static/") ;; static file url prefix /static, in `public` folder   https://libraries.io/clojars/http-kit%2Flein-template
  (not-found "<p>Page not found.</p>")) ;; all other, return 404

(run-server all-routes {:port 8080})



(defn -main [& args]
  (foo "HI!")
  (run-server all-routes {:port 7500}))