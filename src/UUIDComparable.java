package src;

import java.util.UUID;

abstract class UUIDComparable implements Comparable<UUIDComparable>{

    abstract UUID getId();

    public int compareTo(UUIDComparable uuidComparable){

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
            }else{
                return 1;
            }
        }
        return 0;
    }
}
