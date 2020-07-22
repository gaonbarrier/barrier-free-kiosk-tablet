package io.github.gaonbarrier.easykiosk.tablet.network;

public class NetworkSection {
    private int index;

    public NetworkSection(){
        setIndex(findNextIndex());
        getPosition();
        if(getIndex() == 0) {
            Master master = new Master();
            Receiver receiver = new Receiver();
        }
        else {
            Receiver receiver = new Receiver();
        }
    }
    public int getIndex(){return index;}
    public void setIndex(int index){this.index = index;}

    public int findNextIndex(){
        /*
        라우팅테이블을 보고 마지막 인덱스에 1 더해서 return. 일단 초기값 0으로 둔다.
        * */
        return 0;
    }
    public boolean getPosition(/*라우팅테이블을 패러미터로!*/){
        //만약 라우팅 테이블에서 자신의 인덱스가 마스터라면? return true;
        //라우팅 테이블의 index 0는 master로 고정시키는 것 어떨까?
        /*
        귀찮은 작업 하는 것 보단 index 0 가 무조건 master다 라는 것으로 처리 해 보는 것 어떨까?
        * */
        return false;
    }
}
