(defproject adventofcode2019 "0.1.0-SNAPSHOT"
  :description "https://adventofcode.com/2019"
  :url "https://github.com/hannahhenderson/adventofcode2019"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :main ^:skip-aot adventofcode2019.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
