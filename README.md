# food-rocket
음식 주문 및 배달 서비스를 위한 서버 API 입니다.   
클라이언트는 카카오 오븐을 사용해서 화면을 구성하고 각 기능을 정의했습니다.([푸드로켓 화면](https://ovenapp.io/view/wQVIyfiQZ36Ha7ocB2ao9LFWaVcaWcjS/Z6nh4))   

### Git 브랜치 전략
푸드로켓 프로젝트는 Git Flow 전략으로 브랜치를 관리합니다.   
이슈에 대응하여 브랜치를 생성하고 작업한 내용은 Pull Request를 보내 리뷰를 진행한 후 merge를 진행합니다.   

![git flow](https://user-images.githubusercontent.com/25922366/80060110-af28c880-8568-11ea-9bd8-af4031908342.jpg)

|브랜치|설명|
|------|---|
|master|제품으로 출시될 수 있는 브랜치|
|develop|다음 출시 버전을 개발하는 브랜치|
|feature|기능을 개발하는 브랜치|
|release|이번 출시 버전을 준비하는 브랜치|
|hotfix|출시 버전에서 발생한 버그를 수정하는 브랜치|

브랜치 전략 참고 : [우아한형제들 기술 블로그](https://woowabros.github.io/experience/2017/10/30/baemin-mobile-git-branch-strategy.html)
