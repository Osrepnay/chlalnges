#!/bin/sh

mkdir -p numbers

i=1
un="1"
while [ "$i" -le "$1" ]; do
    echo $un > numbers/$i
    i=$((i + 1))
    un="1$un"
done
