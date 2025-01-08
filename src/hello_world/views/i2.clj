(ns hello-world.views.i2
  (:use [hiccup.page :only (html5 include-css include-js)])) ;; Импорт нужных функций hiccup в текущее пространство имен

;; Index page


(def i2
  (html5
    [:head]
    [:body {:style "padding-top: 50px;"}
    [:span {:class "foo"} "bar"]
    [:span {:class "foo" :id "id2"} "gen"]
    [:form
        [:input {:id "message" :type "text"} ]
        [:input {:onclick "wsSendMessage();" :value "Echo" :type "button"} ]
        [:input {:onclick "wsCloseConnection();" :value "Disconnect" :type "button"} ]
    ]
    [:br]
    [:textarea {:id "echoText" :rows "5" :cols "30"}]
    (include-js "/static/js/ws-client.js")




]))

(comment  
      <form>
        <input id="message" type="text">
        <input onclick="wsSendMessage();" value="Echo" type="button">
        <input onclick="wsCloseConnection();" value="Disconnect" type="button">
    </form>


</body>
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