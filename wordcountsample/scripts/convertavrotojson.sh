
if [ "$#" != "1" ]; then
  echo "Usage : $0 <path to avro files>"
  exit 1
fi

inputPath=$1

for fileName in `ls ${inputPath}`; do
  filePath=${inputPath}/${fileName}
  java -jar ../avro-tools-1.8.2.jar tojson ${filePath} > ${filePath}.json
done
