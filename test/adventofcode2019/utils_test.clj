(ns adventofcode2019.utils-test
  (:require [clojure.test :refer :all]
            [adventofcode2019.utils :as utils]))

(deftest file->vector-by-line-works
  (testing "expected values are returned"
    (is (= ["123" "456" "789"]
           (utils/file->vector-by-line "utils-test.txt")))))

(deftest ->int-works
  ; This function needs TLC to actually be robust, it will
  ; blow up if you, for example, use the input "a"
  (testing "vector of string numbers converts properly"
    (is (= [1011 1213 1415]
           (utils/->int ["1011" "1213" "1415"])))))
