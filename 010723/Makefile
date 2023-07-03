clean:
	=rm -rf build

NUMS = 100

.SILENT: build run run-given

build:
	mkdir -p build;                                                            \
	i=1;                                                                       \
	un="1";                                                                    \
	while [ "$$i" -le "$$(($(NUMS) + 11))" ]; do                               \
		echo $$un > build/$$i;                                                 \
		i=$$((i + 1));                                                         \
		un="1$$un";                                                            \
	done;                                                                      \
                                                                               \
	for ifile in build/*; do                                                   \
		if [ -r $$ifile ]; then                                                \
			i=$$(basename $$ifile);                                            \
			three=$$(sed -e 's/^\(111\)\1*$$/0/' $$ifile);                     \
			five=$$(sed -e 's/^\(11111\)\1*$$/0/' $$ifile);                    \
			seven=$$(sed -e 's/^\(1111111\)\1*$$/0/' $$ifile);                 \
			eleven=$$(sed -e 's/^\(11111111111\)\1*$$/0/' $$ifile);            \
			ones=$$(cat $$ifile);                                              \
			onetwenty=$$(sed -e "s/^\\($$ones\\)\\1*\$$/0/" onetwenty);        \
			composite=$$(perl -pe 's/^(11+)\1+$$/0/' $$ifile);                 \
			other=$$(((three == 0) * 1 + (five == 0) * 2 + (seven == 0) * 4)); \
			group=$$(((eleven == 0) * 1 + (onetwenty == 0) * 2));              \
			user=$$((2 + (composite != 0) - (1 == i)));                        \
			chmod $$user$$group$$other $$ifile;                                \
		fi;                                                                    \
	done;                                                                      \

run: build
	for i in $$(ls build | sort -n | head -n$(NUMS)); do                       \
		ifile="build/$$i";                                                     \
		perms=$$(ls -l $$ifile | awk '{print substr($$1,2)}');                 \
		seven=$$((7 - (i % 7) - 1));                                           \
		eleven=$$((11 - (i % 11) - 1));                                        \
		ls build | sort -n | tail -n +$$((i + 1)) | head -n$$(((eleven < seven) * (eleven - seven) + seven)) | awk '{system("ls -l build/"$$0)}' | sed -e 's/^...x....../+/' | grep -q '+'; \
		hasntprime=$$?;                                                        \
		echo $$perms | sed -e 's/\(.w......x\)/Fizz\1/; s/\(.w.....w.\)/Buzz\1/; s/\(.w....r..\)/Rizz\1/; s/\(.w...x...\)/Jazz\1/; s/\(.w..w....\)/Dizz\1/; s/\(.wx......\)/'$$hasntprime'\1/; s/0//; s/1/Prizz/; s/^.........$$/'$$i'/; s/.w.......//' | tr -d '\n'; \
		echo '';                                                               \
	done;

run-given: build
	for i in $$(ls build | sort -n | head -n$(NUMS)); do                       \
		ifile="build/$$i";                                                     \
		perms=$$(ls -l $$ifile | awk '{print substr($$1,2)}');                 \
		seven=$$((7 - ((i - 1) % 7 + 1)));                                     \
		eleven=$$((11 - ((i - 1) % 11) + 1));                                  \
		ls build | sort -n | tail -n +$$((i + 1)) | head -n$$(((eleven < seven) * (eleven - seven) + seven)) | awk '{system("ls -l build/"$$0)}' | sed -e 's/^...x....../+/' | grep -q '+'; \
		hasntprime=$$?;                                                        \
		echo $$perms | sed -e 's/\(.w......x\)/Fizz\1/; s/\(.w.....w.\)/Buzz\1/; s/\(.w....r..\)/Rizz\1/; s/\(.w...x...\)/Jazz\1/; s/\(.w..w....\)/Dizz\1/; s/\(.wx......\)/'$$hasntprime'\1/; s/0//; s/1/Prizz/; s/^.........$$/'$$i'/; s/.w.......//' | tr -d '\n'; \
		echo '';                                                               \
	done;
