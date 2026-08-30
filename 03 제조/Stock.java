public class Stock {
    private String stockCode; // 타겟 주가 코드를 저장
    private double currentPrice; //타겟 주가의 현재 가격을 저장

    //생성자 : 생성자는 반환값이 없다.이 클래스가 처음 생성될 때 뭘 가지고 있어야 하는지
    public Stock(String stockCode){
        this.stockCode = stockCode;
    }

    public void updatePrice(double newPrice){
        this.currentPrice = newPrice;
    }

    public String getCode(){
        return this.stockCode;
    }

    public double getPrice(){
        return this.currentPrice;
    }

}