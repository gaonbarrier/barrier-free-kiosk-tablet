package io.github.gaonbarrier.easykiosk.tablet.menu;

public class MenuLayout2 {
    //DB를 기반으로 개발하게 될 경우를 대비해서 작성해둔 코드
    //아마 테스트타입은 이 레이아웃을 기반으로 개발 할 예정임.
    private Category category;
    //공유하는 ItemList가 필요함. 여기서는 기존의 설계와는 다르게 하나의 Structure로 구성되어야 함.
    public MenuLayout2(){
        /*
        * pseudo code
        *
        * 한 Category 아재를 다 받아들이는 방식으로 할 예정임.
        * Category -> item -> Element 계층 구조는 그대로 하 되
        * 한 아재만 받아들여서 최소한 이중 삼중으로 개지랄을 할 필요는 없겠지
        *
        * DB에서 바로 따와서 가져온다. 뭐 근데 결국 이놈도 Category Name에 대한 정보가 있어야 뭘 하든말든 한다.
        *
        * */

        

    }
}
