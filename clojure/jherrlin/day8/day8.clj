(ns day8
  (:require [clojure.string :as str]))

(comment
  (remove-ns 'day8)
  )

(def test-input
  "30373
25512
65332
33549
35390")

(def input
  (slurp "./input.txt"))

(defn str->int [s]
  (Integer. s))

(defn parse-input [s]
  (->> s
       str/split-lines
       (mapv (comp (partial mapv (comp str->int str)) seq))))

(defn matrix-size [m]
  {:width  (->> m first count)
   :height (->> m count)})

(defn get-x-y [m x y]
  (get-in m [y x]))

(defn direction-north [m start-x start-y]
  (map
   #(get-x-y m start-x %)
   (range (dec start-y) -1 -1)))

(direction-north (parse-input test-input) 4 4)

(defn direction-south [m start-x start-y]
  (let [{matrix-height :height} (matrix-size m)]
    (map
     #(get-x-y m start-x %)
     (range (inc start-y) matrix-height))))

(direction-south (parse-input test-input) 0 0)

(defn direction-west [m start-x start-y]
  (map
   #(get-x-y m % start-y)
   (range (dec start-x) -1 -1)))

(direction-west (parse-input test-input) 4 4)

(defn direction-east [m start-x start-y]
  (let [{matrix-width :width} (matrix-size m)]
    (map
     #(get-x-y m % start-y)
     (range (inc start-x) matrix-width))))

(direction-east (parse-input test-input) 0 0)

(defn highest-tree? [f m start-x start-y]
  (let [value  (get-x-y m start-x start-y)
        values (f m start-x start-y)]
    (if-not (seq values)
      true
      (> value (apply max values)))))

(def highest-tree-north? (partial highest-tree? direction-north))

(highest-tree-north? (parse-input test-input) 2 2)

(def highest-tree-south? (partial highest-tree? direction-south))

(highest-tree-south? (parse-input test-input) 2 2)

(def highest-tree-east? (partial highest-tree? direction-east))

(highest-tree-east? (parse-input test-input) 2 2)

(def highest-tree-west? (partial highest-tree? direction-west))

(highest-tree-west? (parse-input test-input) 2 2)

;; problem 1
(let [matrix                                      (parse-input input #_test-input)
      {matrix-width :width matrix-height :height} (matrix-size matrix)]
  (->> (for [x (range 0 matrix-width) y (range 0 matrix-height)] [x y])
       (map (fn [[x y]]
              (->> [highest-tree-north? highest-tree-east? highest-tree-south? highest-tree-west?]
                   (map (fn [f] (f matrix x y)))
                   (some true?))))
       (remove nil?)
       (count)))
;; => 1849


(defn scenic-score [f m start-x start-y]
  (let [value (get-x-y m start-x start-y)]
    (->> (f m start-x start-y)
         (reduce
          (fn [{:keys [blocking-tree-found?] :as m} n]
            (cond
              blocking-tree-found?
              (update m :trees conj false)
              (>= n value)
              (-> m
                  (update :trees conj true)
                  (assoc :blocking-tree-found? true))
              (< n value)
              (update m :trees conj true)))
          {:blocking-tree-found? false
           :trees                []})
         :trees
         (take-while true?)
         (count))))

(scenic-score direction-north (parse-input test-input) 2 1)   ;; => 1
(scenic-score direction-west  (parse-input test-input) 2 1)   ;; => 1
(scenic-score direction-east  (parse-input test-input) 2 1)   ;; => 2
(scenic-score direction-south (parse-input test-input) 2 1)   ;; => 2

(scenic-score direction-north (parse-input test-input) 2 3)   ;; => 2
(scenic-score direction-west  (parse-input test-input) 2 3)   ;; => 2
(scenic-score direction-south (parse-input test-input) 2 3)   ;; => 1
(scenic-score direction-east  (parse-input test-input) 2 3)   ;; => 2


;; problem 2
(let [matrix                                      (parse-input input #_test-input)
      {matrix-width :width matrix-height :height} (matrix-size matrix)]
  (->> (for [x (range 0 matrix-width) y (range 0 matrix-height)] [x y])
       (map (fn [[x y]]
              (->> [direction-north direction-west direction-south direction-east]
                   (map (fn [f]
                          (scenic-score f matrix x y))))))
       (map #(apply * %))
       (apply max)))
;; => 201600
