(ns day6)

(defn found-start-of-packet? [char-count xs]
  (->> xs
       (map set)
       (first)
       (count)
       (= char-count)))

(defn find-marker-position [char-count s]
  (loop [index 0]
    (let [chars-to-check (->> s
                              (drop index)
                              (partition-all char-count))]
      (if (found-start-of-packet? char-count chars-to-check)
        (+ index char-count)
        (recur (inc index))))))


(find-marker-position 4 "mjqjpqmgbljsphdztnvjfqwrcgsmlb")    ;; => 7
(find-marker-position 4 "bvwbjplbgvbhsrlpgdmjqwftvncz")      ;; => 5
(find-marker-position 4 "nppdvjthqldpwncqszvftbrmjlhg")      ;; => 6
(find-marker-position 4 "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg") ;; => 10
(find-marker-position 4 "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw")  ;; => 11


(def input
  (slurp "./input.txt"))

;; problem 1
(find-marker-position 4 input)  ;; => 1480





(find-marker-position 14 "mjqjpqmgbljsphdztnvjfqwrcgsmlb")     ;; => 19
(find-marker-position 14 "bvwbjplbgvbhsrlpgdmjqwftvncz")       ;; => 23
(find-marker-position 14 "nppdvjthqldpwncqszvftbrmjlhg")       ;; => 23
(find-marker-position 14 "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg")  ;; => 29
(find-marker-position 14 "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw")   ;; => 26




;; problem 2
(find-marker-position 14 input)    ;; => 2746
