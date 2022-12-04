(ns day4
  (:require [clojure.string :as str]
            [clojure.set :as set]))

(def test-input
  "2-4,6-8
2-3,4-5
5-7,7-9
2-8,3-7
6-6,4-6
2-6,4-8")

(def input
  (slurp "./input.txt"))

(defn str->int [s]
  (Integer. s))



(str->int "10")                                   ;; => 10
(set/subset? #{3 4 5 6 7} #{2 3 4 5 6 7 8})       ;; => true
(set/intersection #{2 3 4 5 6 7 8} #{3 4 5 6 7})  ;; => #{7 4 6 3 5}
(sort-by count [#{3 4 5 6 7} #{2 3 4 5 6 7 8}])   ;; => (#{7 4 6 3 5} #{7 4 6 3 2 5 8})


;; problem 1
(->> input #_test-input
     (str/split-lines)
     (map #(str/split % #"[,-]"))
     (mapv (fn [row]
             (let [[a1 a2 b1 b2] (mapv str->int row)
                   r1            (set (range a1 (inc a2)))
                   r2            (set (range b1 (inc b2)))]
               (apply set/subset? (sort-by count [r1 r2])))))
     (filter true?)
     count)

;; problem 2
(->> input #_test-input
     (str/split-lines)
     (map #(str/split % #"[,-]"))
     (mapv (fn [row]
             (let [[a1 a2 b1 b2] (mapv str->int row)
                   r1            (set (range a1 (inc a2)))
                   r2            (set (range b1 (inc b2)))]
               (set/intersection r1 r2))))
     (remove empty?)
     (count))
