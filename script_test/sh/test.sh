#!/bin/sh
echo hello world

echo "\n1. test basic: "
var1='_var1'
echo "print var1: ${var1}"

var2="_var2(${var1})"
echo "print var2: ${var2}"

var3="_var3"
readonly var3
echo "print var3: ${var3}"
# var3="change var3"

var4="_var4"
echo "print var4: ${var4}"
unset var4
echo "print var4: ${var4}"

echo "\n2. test string: "
string="12345"
echo "string: ${string}"
echo "len: ${#string}"
# echo `expr index "$string" 3`   # mac has no index function

echo "\n3. test array: "
array=(1 2 3 4)
echo ${array[@]}
echo ${array[*]}
echo ${array[0]}
echo ${#array[*]}

echo "\n4. test comment: "
# single comment
:<<EOF
multiple lines comment
EOF

:<<!
multiple lines comment2
?
!

echo "\n5. test param: "
echo "name: ${0}"
echo "param1: ${1}"
echo "param2: ${2}"
echo "param3: ${3}"
echo "params count: ${#}"
echo "all params: ${*}"
echo "all params: ${@}"
echo "current ID: $$"
echo "last ID: $!"
echo "current option: $-"
echo "return status: $?"

for i in "$*";do
  echo $i
done

for i in "$@";do
  echo $i
done

echo "\n6. test arithmetic operations: "
a=10
b=20
echo "a = $a , b = $b"
echo 'a + b = '$(($a + $b))
echo 'a - b = '$(($a - $b))
echo 'a * b = '$(($a * $b))
echo 'b / a = '$(($b / $a))
echo 'a % b = '$(($a % $b))

if [ $a == $b ]
then
  echo a==b
fi

if [ $a != $b ]
then
  echo a!=b
fi

echo "\n7. test realtive operations: "
if [ $a -eq $b ]
then
  echo "$a == $b is true"
else
  echo "$a == $b is false"
fi

if [ $a -ne $b ]
then
  echo "$a != $b is true"
else
  echo "$a != $b is false"
fi

if [ $a -gt $b ]
then
  echo "$a > $b is true"
else
  echo "$a > $b is false"
fi

if [ $a -lt $b ]
then
  echo "$a < $b is true"
else
  echo "$a < $b is false"
fi

if [ $a -ge $b ]
then
  echo "$a >= $b is true"
else
  echo "$a >= $b is false"
fi

if [ $a -le $b ]
then
  echo "$a <= $b is true"
else
  echo "$a <= $b is false"
fi

echo "\n8. test boolean operations: "
if [ ! true ]
then
  echo "! true is true"
else
  echo "! true is false"
fi

if [ 1 == 2 -o 1 == 3 ]
then 
  echo "false | false is true"
else 
  echo "false | false is false"
fi

if [ 1 == 2 -o 1 == 1 ]
then 
  echo "false | true is true"
else 
  echo "false | true is false"
fi

if [ 1 == 2 -a 1 == 3 ]
then 
  echo "false & false is true"
else 
  echo "false & false is false"
fi

if [ 1 == 1 -a 1 == 1 ]
then 
  echo "true & true is true"
else 
  echo "true & true is false"
fi

echo "\n9. test logic operations: "
if [[ true && true ]]
then
  echo "true && true is true"
else
  echo "true && true is false"
fi

if [[ true && false ]]
then
  echo "true && false is true"
else
  echo "true && false is false"
fi

if [[ true || true ]]
then
  echo "true || true is true"
else
  echo "true || true is false"
fi

if [[ true || false ]]
then
  echo "true || false is true"
else
  echo "true || false is false"
fi

if [[ false || false ]]
then
  echo "false || false is true"
else
  echo "false || false is false"
fi

