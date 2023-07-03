#!/bin/sh

for ifile in numbers/*; do
    if [ -r $ifile ]; then
        i=$(basename $ifile)
        three=$(sed -e 's/^\(111\)\1*$/0/' $ifile)
        five=$(sed -e 's/^\(11111\)\1*$/0/' $ifile)
        seven=$(sed -e 's/^\(1111111\)\1*$/0/' $ifile)
        eleven=$(sed -e 's/^\(11111111111\)\1*$/0/' $ifile)
        ones=$(cat $ifile)
        onetwenty=$(sed -e "s/^\\($ones\\)\\1*\$/0/" onetwenty)
        # composite=$(sed -e 's/^\(11\(1\)*\)\1*$/0/' $ifile)
        composite=$(perl -pe 's/^(11+)\1+$/0/' $ifile)
        other=$(((three == 0) * 1 + (five == 0) * 2 + (seven == 0) * 4))
        group=$(((eleven == 0) * 1 + (onetwenty == 0) * 2))
        user=$((2 + (composite != 0) - (1 == i)))
        chmod $user$group$other $ifile
    fi
done
