(ns hello-world.core
 (:require [org.httpkit.server :as server]
           [clostache.parser :as mustache]
           [net.cgrand.enlive-html :as html]
           [hello-world.views.index :refer [index-page]]
           )
 (:use [compojure.route :only [files not-found]]
       [compojure.core :only [defroutes GET POST DELETE ANY context]]  ;;https://http-kit.github.io/server.html#routing
       org.httpkit.server))

(comment
(ns ring.core
  (:use ring.adapter.jetty))
)


(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))   

(defn mustache_render_plain [request]
  {:status 200
   :headers {"Content-Type" "text/plain"}
   :body (mustache/render "Hello, {{name}}!" {:name "Felix"})})

(defn enlive_page [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body "<h1>enlive_page</h1>"})


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


(defn mustache_render_ws_template [request]
  (foo (mustache/render-resource "templates/ws.html"
                                 {}))

  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (mustache/render-resource "templates/ws.html"
                                 {})})  ;  

(html/deftemplate index2 "templates/ws.html"
  [ctxt]
  [:p#message] (html/content (:message ctxt)))



(defn render
  [nodes]
  (apply str nodes))

(defn enlive_handler [request]
(foo "\n\n\n\n enlive handler rendered::")
(foo (render (html/deftemplate main-template "templates/application.html"
  []
  [:head :title] (html/content "Enlive starter kit"))))

  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (html/deftemplate main-template "templates/application.html"
  []
  [:head :title] (html/content "Enlive starter kit"))})  ;                                   





(defn chat-handler [request]
  (with-channel request channel
    (on-close channel (fn [status] (println "client close it" status)))
    (on-receive channel (fn [data] ;; echo it back
                          (send! channel data)))))

(comment 
(html/deftemplate main-template "templates/html_file_above.html"
  []
  [:head :title] (html/content "Enlive starter kit"))
)

(defroutes all-routes
  (GET "/" [] mustache_render_plain)
  (GET "/template" [] mustache_render_template)
  (GET "/ws_template" [] mustache_render_ws_template);;wrk -t12 -c400 -d30s http://127.0.0.1:8080/ws
  (GET "/img" [] mustache_render_template_with_static)
  (GET "/enlive" [] enlive_page)
  (GET "/indx_handler" [] index-page)
  (GET "/enlive_temp" [] enlive_handler)
  (GET "/ws2" [] index2)

  (GET "/ws" [] chat-handler)     ;; websocket

  (files "/static/") ;; static file url prefix /static, in `public` folder   https://libraries.io/clojars/http-kit%2Flein-template
  (not-found "<p>Page not found.</p>")) ;; all other, return 404




(defn -main [& args]
  (foo "HI!")
  (run-server all-routes {:port 11111}))