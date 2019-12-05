(ns adventofcode2019.day02.intcodes
  (:require [adventofcode2019.utils :as utils]))

;; PART 2: INTCODES
(defn update-list [address integers fn-op]
  (let [n1-adr (get integers (+ 1 address))
        n2-adr (get integers (+ 2 address))
        result-adr (get integers (+ 3 address))
        n1 (get integers n1-adr)
        n2 (get integers n2-adr)]
    (assoc integers result-adr (fn-op n1 n2))))

(defn process-memory [int-list]
  (loop [instruction-pointer 0
         integers int-list]
    (let [opcode (get integers instruction-pointer)
          next-ip (+ instruction-pointer 4)] ; 4 is based on the number of inputs to an instruction (1 opcode + 3 parameters)
                                             ; The termination instruction only has 1
      (condp = opcode
        1 (recur next-ip (update-list instruction-pointer integers +))
        2 (recur next-ip (update-list instruction-pointer integers *))
        99 integers
        "something went wrong"))))

; noun and verb are always between 0 and 99, inclusive
(defn reset-last-state [int-list noun verb]
  (-> (into [] int-list)
      (assoc 1 noun)
      (assoc 2 verb)))

(defn file->inputs
  [filename]
  (-> filename
      utils/file->comma-free-vector
      utils/->int))

(defn combos->output-val
  [inputs noun verb]
  (-> inputs
      (reset-last-state noun verb)
      process-memory
      first))

(defn try-combos [inputs]
  ; This is a brute-force fn, it isn't elegantly optimized
  (for [noun (range 0 99)
        verb (range 0 99)
        :when (= 19690720 (combos->output-val inputs noun verb))]
    [noun verb]))

(defn file->processed-memory
  [filename]
  (let [inputs (file->inputs filename)
        [[noun verb]] (try-combos inputs)]
    (println "noun: " noun ", verb: " verb)
    (+ (* 100 noun) verb)))

(println "Final val: " (file->processed-memory "day02/opcodes.txt"))
