(ns adventofcode2019.day04.secure-container
  (:require [adventofcode2019.utils :as utils]))

;You arrive at the Venus fuel depot only to discover it's protected by a password. The Elves had written the password on a sticky note, but someone threw it out.
;
;However, they do remember a few key facts about the password:
;
;It is a six-digit number.
;The value is within the range given in your puzzle input.
;Two adjacent digits are the same (like 22 in 122345).
;Going from left to right, the digits never decrease; they only ever increase or stay the same (like 111123 or 135679).
;Other than the range rule, the following are true:
;
;111111 meets these criteria (double 11, never decreases).
;223450 does not meet these criteria (decreasing pair of digits 50).
;123789 does not meet these criteria (no double).
;How many different passwords within the range given in your puzzle input meet these criteria?
;
;Your puzzle input is 125730-579381.

(defn digit-vector [pw]
  (loop [n pw
         digits []]
    (if (= 0 n)
      digits
      (recur (quot n 10) (concat [(mod n 10)] digits)))))

(defn digits-increase?
  [pw-vector]
  (loop [nums pw-vector
         increase? true]

    (cond
      (= 1 (count nums)) ; the last digit is checked with the 2nd-to-last one
      increase?

      (<= (first nums) (second nums))
      (recur (rest nums) true)

      :else
      false)))

(defn has-adjacent-digits?
  [pw-vector]
  (loop [nums pw-vector
         adjacent? false]
    (cond
      (= 1 (count nums))
      adjacent?

      (= (first nums) (second nums))
      true

      :else
      (recur (rest nums) false))))

(defn is-valid?
  [pw]
  (let [pw-vector (digit-vector pw)]
    (and (has-adjacent-digits? pw-vector)
         (digits-increase? pw-vector))))

(defn not-valid?
  ; double negatives are confusing
  [pw]
  (not (is-valid? pw)))

(defn total-valid-passwords
  [range-start range-end]
  (->> (range range-start (+ 1 range-end))  ; include range start and end
       (remove not-valid?)
       count))

;TODO: Uncomment me to get the answer -- I'm a poorly optimized bit of logic!
;(println "result is: " (total-valid-passwords 125730 579381))