#!/bin/sh

for i in $(ls numbers | sort -n); do
    ifile="numbers/$i"
    perms=$(ls -l $ifile | awk '{print substr($1,2)}')
    seven=$((7 - (i % 7) - 1))
    eleven=$((11 - (i % 11) - 1))
    ls numbers | sort -n | tail -n +$((i + 1)) | head -n$(((eleven < seven) * (eleven - seven) + seven)) | awk '{system("ls -l numbers/"$0)}' | sed -e 's/^...x....../+/' | grep -q '+'
    hasntprime=$?
    echo $perms | sed -e 's/\(.w......x\)/Fizz\1/; s/\(.w.....w.\)/Buzz\1/; s/\(.w....r..\)/Rizz\1/; s/\(.w...x...\)/Jazz\1/; s/\(.w..w....\)/Dizz\1/; s/\(.wx......\)/'$hasntprime'\1/; s/0//; s/1/Prizz/; s/^.........$/'$i'/; s/.w.......//' | tr -d '\n'
    echo ''
done
