(ns adventofcode2019.day02.opcodes
  (:require [adventofcode2019.utils :as utils]))

;; PART 1: OPSCODE
(defn update-list [pos integers fn-op]
  (let [n1-pos (get integers (+ 1 pos))
        n2-pos (get integers (+ 2 pos))
        result-pos (get integers (+ 3 pos))
        n1 (get integers n1-pos)
        n2 (get integers n2-pos)]
    (assoc integers result-pos (fn-op n1 n2))))

(defn process-opcode [int-list]
  (loop [pos 0
         integers int-list]
    (let [opcode (get integers pos)
          next-pos (+ pos 4)]
      (condp = opcode
        1 (recur next-pos (update-list pos integers +))
        2 (recur next-pos (update-list pos integers *))
        99 integers
        "something went wrong"))))

(defn reset-last-state [int-list]
  (-> (into [] int-list)
      (assoc 1 12)
      (assoc 2 2)))

(defn file->processed-opcodes
  [filename]
  (-> filename
      utils/file->comma-free-vector
      utils/->int
      reset-last-state
      process-opcode))

(println "Final position 0 val: " (first (file->processed-opcodes "day02/opcodes.txt")))
