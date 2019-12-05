(ns adventofcode2019.day02.intcodes-test
  (:require [clojure.test :refer :all]
            [adventofcode2019.day02.intcodes :as itc]))

; Note: These tests haven't been updated from the opcodes ones
;       They could do with a little more fleshing out, perhaps
;       validating that try-combos-works, etc.

(deftest update-list-works
  (testing "base case"
    (is (= [1 9 10 70 2 3 11 0 99 30 40 50]
           (itc/update-list 0 [1 9 10 3 2 3 11 0 99 30 40 50] +)))
    (is (= [3500 9 10 70 2 3 11 0 99 30 40 50]
           (itc/update-list 4 [1 9 10 70 2 3 11 0 99 30 40 50] *)))))

(deftest process-opcode-works
  (testing "when opcode is 1, fn-op is addition"
    (is (= [2 0 0 0 99]
           (itc/process-memory [1 0 0 0 99]))))
  (testing "when opcode is 2, fn-op is multiplication"
    (is (= [2 3 0 6 99]
           (itc/process-memory [2 3 0 3 99]))))
  (testing "improper values return a text error"
    ; Decided against throwing because I'm just running this locally
    (is (= "something went wrong"
           (itc/process-memory [3 4 5 3 7 8]))))
  (testing "more examples"
    (is (= [3500 9 10 70 2 3 11 0 99 30 40 50]
           (itc/process-memory [1 9 10 3 2 3 11 0 99 30 40 50])))
    (is (= [2 4 4 5 99 9801]
           (itc/process-memory [2 4 4 5 99 0])))
    (is (= [30 1 1 4 2 5 6 0 99]
           (itc/process-memory [1 1 1 4 99 5 6 0 99])))))

