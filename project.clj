(defproject hello-world "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :main hello-world.core
  :dependencies [[org.clojure/clojure "1.12.0"]
                 [ring/ring-core "1.7.1"]
                 [compojure "1.6.0"]  
                 [http-kit "2.8.0"]
                 [de.ubercode.clostache/clostache "1.4.0"]]
  :repl-options {:init-ns hello-world.core})
