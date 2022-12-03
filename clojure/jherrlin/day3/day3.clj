(ns day3
  (:require [clojure.string :as str]
            [clojure.set :as set]))


(defn char-range [start end]
  (map char (range (int start) (inc (int end)))))

(def test-input
  "vJrwpWtwJgWrhcsFMMfFFhFp
jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
PmmdzqPrVvPwwTWBwg
wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
ttgJtRGJQctTZtZT
CrZsJsPPZsGzwwsLwLmpwMDw")

(def priorities
  "{\\A 26,
  \\a 0,
  \\B 27,
  \\b 1,
  \\C 28,
  ...}"
  (->> (map-indexed
        (fn [idx v]
          [v (inc idx)])
        (concat
         (char-range \a \z)
         (char-range \A \Z)))
       (into {})))

(defn split-on-half
  [xs]
  [(take (/ (count xs) 2) xs)
   (take (/ (count xs) 2) (reverse xs))])

(def input
  (slurp "./input.txt"))

;; problem 1
(->> input #_test-input
     str/split-lines
     (map split-on-half)
     (map (fn [[c1 c2]]
            (set/intersection (set c1) (set c2))))
     (apply concat)
     (map priorities)
     (apply +))
;; => 7446


;; problem 2
(->> input #_test-input
     str/split-lines
     (partition-all 3)
     (map (fn [[u1 u2 u3]]
            (set/intersection (set u1) (set u2) (set u3))))
     (apply concat)
     (map priorities)
     (apply +))
;; => 2646
