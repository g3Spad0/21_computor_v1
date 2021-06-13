if (( "$#" != 1 ))
 then
    echo 'Arg not exists'
    exit 1
fi

javac src/*.java
java -cp src  Main "$1" "fromBash"