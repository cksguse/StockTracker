### 기본 설계(클래스)
- AlertService(모든 클래스의 조율을 책임짐)
- Stock(고객 타겟 주식의 현재 가격을 책임짐)
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
1. APIReader가 API를 호출함
2. AlertService가 User -> AlertRuleRepository -> AlertRule 순으로 타겟 주식과 타겟 조건을 획득해 옴
3. 
