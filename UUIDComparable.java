import java.util.UUID;

abstract class UUIDComparable implements Comparable<UUIDComparable> {

    abstract UUID getId();

    public int compareTo(UUIDComparable uuidComparable) {

        /*HashMap<Character, Integer> map = new HashMap<>();
        map.put('a',10);
        map.put('b',11);
        map.put('c',12);
        map.put('d',13);
        map.put('e',14);
        map.put('f',15);
        */

        String o1String = this.getId().toString();
        String o2String = uuidComparable.getId().toString();


        for (int i = 0; i < 36; i++) {
            char o1Character = o1String.charAt(i);
            char o2Character = o2String.charAt(i);

            if(o1Character == o2Character){
                continue;
            }
            if(o1Character < o2Character){
                return -1;
            }
            return 1;


/*
            if(isDigit(o1Character) && isDigit(o2Character)){
                if(o1Character==o2Character) continue;
                if(Character.getNumericValue(o1Character) < Character.getNumericValue(o2Character)) return -1;
                return 1;

            }
            if(!isDigit(o1Character) && !isDigit(o2Character)){
                if(String.valueOf(o1Character).equals(String.valueOf(o2Character))) continue;
                if(map.get(o1Character) < map.get(o2Character)) return -1;
                return 1;
            }
            if(isDigit(o1Character)){
                return -1;
            }else {
                return 1;
            }
*/
        }
        return 0;// 1 / (1.2 * 10^24)
    }



}
