### 기본 설계(클래스)

- AlertService(모든 클래스의 조율을 책임짐)
- Stock(고객 타겟 주식의 현재 가격을 책임짐)
- StockRepository(타겟 주식들을 보존)
- PriceChecker(고객의 타겟 가격과 현재 가격의 비교를 책임짐)
- AlertRule(고객이 타겟한 주식과 타겟 조건을 책임짐)
- AlertRuleRepository(유저 한명이 가지고 있는 alertrule의 리스트 관리를 책임짐)
- User(고객 연락처 정보를 책임짐)
- Notifier(고객에게의 에러 메세지 혹은 알람을 책임짐)
- APIReader(외부 API로부터의 수신을 책임짐)
- Scheduler(API 수신의 기동 스케줄을 책임짐)

### 상세 설계(시그니처, 호출 순서)(애자일:Just Enough Design)

> 유저가 알람을 등록하고, 조건을 이미 만족하는지 확인하는 플로

1. 유저가 유저 정보(이름, 이메일), 타겟 주식, 타겟 가격과 조건을 입력
2. Main에서 User 객체 생성(유저 정보, 유저 코드, AlertRuleRespository 코드)
3. Main에서 AlertRuleRepository 객체 생성(AlertRuleRepository 코드, AlertRule 리스트)
4. AlertRuleRepository가 AlertRule 객체 생성(타겟 주식, 타겟 가격)

#### 데이터를 훑는 플로

1. Scheduler가 alertService.checkAllAlerts() 호출
2. AlertService가 repository.getAll()로 전체 AlertRule 목록을 받음 (User 경유 없음, 1중 반복)
3. 목록을 하나씩 반복하며 각 AlertRule에 대해:
   a. rule에서 Stock 정보를 꺼냄
   b. APIReader로 최신 가격을 받아옴
   c. stock.updatePrice(최신가격) 로 갱신
   d. PriceChecker.isConditionMet(stock, rule)로 판단
   e. 조건 충족 시 → rule.getUser()로 유저 정보 꺼내서 Notifier에게 전달
4. 반복 종료

#### 클래스 별 변수 메소드

1. Stock
   a. String stockCode
   b. Double currentPrice
   c. updatePrice()
