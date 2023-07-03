# 010723

`make build` generates and tags the numbers in file permissions, `make run` builds and outputs FizzBuzz++ from the permissions.

Default makefile requires perl. If perl is not installed and you don't want to install perl for some reason, you can use Makefile-perlless which uses sed for the prime-finding regex, but for some reason sed has a lot worse time complexity here and takes forever for FizzBuzz++ up to 100, so the default there is only 10 (can be changed in both makefiles in the `NUMS` macro). There used to be a version that allowed usage with a `grep` with the `-P` flag, but I think I deleted it and the less time I spend on this code the better.

The scripts are not necessary, they are simply normal shell versions of what's in the makefiles. `make build` is equivalent to a gen with a tag, `make run` does an output.
