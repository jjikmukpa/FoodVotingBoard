💻 프로젝트 소개

______________________________________________________________

⌨️ 개발 기간

______________________________________________________________

🧑‍🤝‍🧑 멤버구성
팀장 : 권은혜
형상관리자 : 홍주연
팀원 : 양혜연
팀원 : 박효찬
______________________________________________________________

⚙️ 개발 환경

______________________________________________________________

📂 패키지구조
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
______________________________________________________________

📌 주요 기능

______________________________________________________________

🖱️ 기능명

______________________________________________________________

기능내용

📋 게시판관리
🗣️ 후기
권은혜: 
홍주연: 
양혜연: 
박효찬: 
____________________________________________________

감사합니다.
