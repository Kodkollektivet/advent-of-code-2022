(ns day1
  (:require [clojure.string :as str]))


(def test-input
  "1000
2000
3000

4000

5000
6000

7000
8000
9000

10000")

(def input
  (slurp "./input.txt"))

(defn str->int [s]
  (Integer. s))

(defn split-each-elf [s]
  (str/split s #"\n\n"))

(defn parse-for-each-elf [elf]
  (->> (str/split-lines elf)
       (mapv str->int)))

(defn elfs-with-calories [s]
  (->> s
       split-each-elf
       (map-indexed (fn [idx elf]
                      (let [numbers (parse-for-each-elf elf)]
                        [(inc idx) (apply + numbers) numbers ])))
       (into [])))

;; problem 1
(->> input #_test-input
     (elfs-with-calories)
     (sort-by second #(compare %2 %1))
     first
     second)

;; problem 2
(->> input #_test-input
     (elfs-with-calories)
     (sort-by second #(compare %2 %1))
     (take 3)
     (map second)
     (apply +))
