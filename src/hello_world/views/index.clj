(ns hello-world.views.index
  (:use [hiccup.page :only (html5 include-css include-js)])) ;; Импорт нужных функций hiccup в текущее пространство имен

;; Index page

(defn sum2 
([] 0)
([z] z)
([x y] (+ x y)))
(comment     
(defn sum2 
  []    0
  [x y] (+ x y))
)

(def index-page
  (html5
    [:head
      (include-css "https://unpkg.com/bootstrap@3.3.7/dist/css/bootstrap.min.css")]
    [:body {:style "padding-top: 50px;"}
      [:div.container
        [:div.form-group [:input#message.form-control {:name "message" :type "text"}]]
        [:button.btn.btn-primary {:name "send-btn"} "Send"]]
      [:hr]
      [:div.container
        [:div#chat]]
      (include-js "js/ws-client.js")
]))

(comment  

[:span {:class "foo"} "bar"]
<span class="foo">bar</span>

<body>
    <form>
        <input id="message" type="text">
        <input onclick="wsSendMessage();" value="Echo" type="button">
        <input onclick="wsCloseConnection();" value="Disconnect" type="button">
    </form>
    <br>
    <textarea id="echoText" rows="5" cols="30"></textarea>
</body>
</html>

)