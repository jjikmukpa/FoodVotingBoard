<h1>💟Jjikmukpa💟</h1>

<h2>💻 프로젝트 소개</h2>
<p>
현재 대한민국의 인터넷 커뮤니티 사이트는 많은 발전을 이루었고, 
사람들이 정보를 접하는 방식도 뉴스나 신문을 직접 보는 방식에서
인터넷 기사를 읽고 소통하는 방식으로 바뀌었습니다.
웹 사이트 사용자가 늘어난 만큼 커뮤니티 사이트도 늘어났지만,
주로 게임과 일상 관련에 대한 커뮤니티가 늘어났고,
음식에 관련된 커뮤니티는 많이 찾아볼 수 없었습니다.

그래서 찍먹파 팀은 음식 게시판을 계획해,
음식에 대한 소통을 함께하고, 음식 관련 논쟁을 펼칠 수 있는 게시판을 제작했습니다.
음식 게시판에서는 좋아하는 음식과 음식점을 소개하고, 
맛있게 먹었던 음식에 대한 리뷰를 게시판을 통해 공유할 수 있습니다.

또한 논쟁 게시판에서는 음식에 대한 다양한 주제로, 
음식을 먹는 방법 또는 선호하는 음식에 대해 논쟁하고 투표할 수 있습니다.

너무 많은 정보에 지친 사용자들이 가벼운 주제로 대화를 즐기며
유용하고 다양한 정보들을 공유할 수 있기를 기대하고 있습니다.
</p>

<h2>⌨️ 개발 기간</h2>


<h2>🧑‍🤝‍🧑 멤버구성</h2>
팀장 : 권은혜
형상관리자 : 홍주연
팀원 : 양혜연
팀원 : 박효찬


<h2>⚙️ 개발 환경</h2>



<h2>📂 패키지구조</h2>

```
+---java
|   \---com
|       \---jjikmukpa
|           \---project
|               |   FoodVotingBoardApplication.java
|               |
|               +---admin
|               |   \---controller
|               |           AdminController.java
|               |
|               +---auth
|               |   +---controller
|               |   |       AuthController.java
|               |   |
|               |   +---principal
|               |   |       AuthPrincipal.java
|               |   |
|               |   \---service
|               |           AuthService.java
|               |
|               +---common
|               |       Pagenation.java
|               |       PagingButtonInfo.java
|               |
|               +---config
|               |       BeanConfiguration.java
|               |       CustomAuthenticationFailureHandler.java
|               |       CustomUsernamePasswordAuthenticationFilter.java
|               |       UserDeletedException.java
|               |       WebSecurityConfig.java
|               |
|               +---exception
|               |       ResourceNotFoundException.java
|               |
|               +---main
|               |   \---controller
|               |           MainController.java
|               |
|               +---member
|               |   +---controller
|               |   |       MemberController.java
|               |   |
|               |   +---model
|               |   |   +---dto
|               |   |   |       SignupDTO.java
|               |   |   |
|               |   |   \---entity
|               |   |           Member.java
|               |   |           RoleType.java
|               |   |           Status.java
|               |   |
|               |   +---repository
|               |   |       MemberRepository.java
|               |   |
|               |   \---service
|               |           MemberService.java
|               |
|               +---notice
|               |   +---controller
|               |   |       NoticeController.java
|               |   |
|               |   +---model
|               |   |   +---dto
|               |   |   |       NoticeDTO.java
|               |   |   |
|               |   |   \---entity
|               |   |           Notice.java
|               |   |
|               |   +---repository
|               |   |       NoticeRepository.java
|               |   |
|               |   \---service
|               |           NoticeService.java
|               |
|               +---post
|               |   +---controller
|               |   |       DebatePostController.java
|               |   |       PostController.java
|               |   |
|               |   +---model
|               |   |   +---dto
|               |   |   |       CreateDebatePostDTO.java
|               |   |   |       CreatePostDTO.java
|               |   |   |       DebatePostDTO.java
|               |   |   |       PostDTO.java
|               |   |   |
|               |   |   \---entity
|               |   |           DebatePost.java
|               |   |           Post.java
|               |   |
|               |   +---repository
|               |   |       DebatePostRepository.java
|               |   |       PostRepository.java
|               |   |
|               |   \---service
|               |           DebatePostService.java
|               |           PostService.java
|               |
|               +---reply
|               |   +---model
|               |   |   \---entity
|               |   |           Reply.java
|               |   |
|               |   +---repository
|               |   |       ReplyRepository.java
|               |   |
|               |   \---service
|               |           ReplyService.java
|               |
|               \---verification
|                   |   MailManager.java
|                   |   SHA256Util.java
|                   |
|                   \---controller
|                           MailController.java
|
\---resources
    |   application.yml
    |
    +---static
    |   +---css
    |   |       changePW.css
    |   |       changePwNotLoggedIn.css
    |   |       createdebatepost.css
    |   |       createpost.css
    |   |       debatePost.css
    |   |       detaildebatepost.css
    |   |       detailPost.css
    |   |       findId.css
    |   |       findPw.css
    |   |       header.css
    |   |       info.css
    |   |       login.css
    |   |       main.css
    |   |       mypage.css
    |   |       myPosts.css
    |   |       post.css
    |   |       signup.css
    |   |
    |   +---image
    |   |       main_image.png
    |   |       tears1.png
    |   |       tears2.png
    |   |       tears3.png
    |   |
    |   \---js
    |           changePw.js
    |           createdebatepost.js
    |           debatepost.js
    |           detaildebatepost.js
    |           info.js
    |           main.js
    |
    \---templates
        |   index.html
        |
        +---fragments
        |       header.html
        |
        \---layout
            +---admin
            |       admin.html
            |
            +---auth
            |       login.html
            |
            +---error
            |       accessDenied.html
            |
            +---main
            |       main.html
            |
            +---member
            |       changePw.html
            |       changePwNotLoggedIn.html
            |       findId.html
            |       findPw.html
            |       info.html
            |       mypage.html
            |       myPosts.html
            |       signup.html
            |
            +---notice
            |       notice.html
            |
            \---post
                |   createPost.html
                |   detailPost.html
                |   modifyPost.html
                |   post.html
                |
                \---debatePost
                        createDebatePost.html
                        debatePost.html
                        detailDebatePost.html
                        
```
<h2>📌 주요 기능</h2>



<h2>🖱️ 기능명</h2>



<h2>💫기능내용</h2>

<h2>📋 게시판관리</h2>

<h2>🗣️ 후기</h2>
권은혜: 
홍주연: 
양혜연: 
박효찬: 


<h3>감사합니다.</h3>
