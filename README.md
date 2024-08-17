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
<p>
[7월 10일 - 7월 31일]<br>
 - 프로젝트 계획, 피그마 제작<br>
<br>
[8월 1일-8월 13일]<br>
 - 팀 회의, 기능 구현<br>
<br>
[8월 14일]<br>
 - 팀별 발표<br>
</p>

<h2>🧑‍🤝‍🧑 멤버구성</h2>
<p>
❤ 팀장 | 권은혜 <br>
❤ 형상관리자 | 홍주연 <br>
❤ 팀원 | 양혜연 <br>
❤ 팀원 | 박효찬 <br>
</p>

<h2>⚙️ 개발 환경</h2>

![skills](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![skills](https://img.shields.io/badge/HTML-239120?style=for-the-badge&logo=html5&logoColor=white)
![skills](https://img.shields.io/badge/CSS-239120?&style=for-the-badge&logo=css3&logoColor=white)
![skills](https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=JavaScript&logoColor=white)
![skills](https://img.shields.io/badge/jQuery-0769AD?style=for-the-badge&logo=jquery&logoColor=white)
![skills](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white)
![skills](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![skills](https://img.shields.io/badge/Figma-F24E1E?style=for-the-badge&logo=figma&logoColor=white)

![skills](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)
![skills](https://img.shields.io/badge/GIT-E44C30?style=for-the-badge&logo=git&logoColor=white)
![skills](https://img.shields.io/badge/IntelliJ_IDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)
![skills](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![skills](https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=Spring-Security&logoColor=white)
![skills](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)

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
<p>
 📌 로그인(아이디 기억, 아이디 찾기, 비밀번호 찾기) <br>
 📌 회원가입(아이디 중복 확인, 닉네임 중복 확인, 비밀번호 확인, 이메일 인증) <br>
 📌 일반(음식)게시판 게시글/댓글 조회, 작성, 수정, 삭제 <br>
 📌 논리 게시판 게시글 조회, 작성, 수정, 삭제, 사진기능, 투표기능, 조회수 기능 <br>
 📌 마이페이지 개인정보 조회, 비밀번호 변경, 회원 탈퇴, 내 게시글 조회 <br> 
</p>

<h2>🖱️ 기능명 | 💫기능내용</h2>
<h3>🎈논리게시판🎈</h3>
<p>
 
- 논리게시판 | 페이징(내림차순)- 나중에 작성한글을 먼저 보이게 해서 정렬함, 
    검색- 단어를 검색해서 제목+내용이 있으면 보이게 함
     일반게시판 버튼 - 일반게시판으로 링크를 걸어서 누르면 일반게시판으로 이동함 
    게시판 - 번호, 제목, 작성자, 작성일, 조회수를 보여주고, 게시글을 누르면 그
                            게시글의 상세페이지로 이동
    조회수 - 게시글을 누르면 조회수가 증가한다.

- 논리게시판 (글쓰기) : 임시저장 - 자신이 작성한 제목, 내용, 저장시킨 사진을 임시저장해 다시 글을 쓸 때 섰던
    것을 가져올 수 있다.
               사진추가 - 사진을 추가 하면 미리보기를 통해 자신이 어떤 사진을 올렸는지 알 수 있고,
                  디렉토리에 사진을 저장하고 그 경로를 DB에 저장함                                     
    저장 - 자신이 글쓰기에서 작성한 글을 DB에 저장한다.
   목록 - 목록버튼을 누르면 논리 게시판으로 이동한다.

- 논리게시판(상세페이지) : 상세페이지 - 글쓰기에서 작성한 것(제목, 내용, 사진)을 상세페이지를 통해 보여준다.
       투표 기능 -  사진 둘중에 하나를 선택하면 '사진을 저장하시겠습니까?' 라는 창을 보여주고
                               확인을 누르면 클릭한 사진에 투표수가 증가한다. 
</p>
<h3>🎇일반게시판🎇</h3>
<p>

- 일반게시판: 게시글을 보여주고 게시글을 누르면 상세페이지로 넘어가게 처리, <br>
            페이징처리 - 내림차순 정리. 최근순으로 정렬. 한 페이지당 게시글 10개만 보이게 처리, <br>
            검색 - 제목이나 내용을 검색하면 해당하는 게시글만 나오도록 처리, <br>
            논쟁게시판 버튼 - 누르면 논쟁게시판 페이지로 넘어가도록 처리

- 일반게시판 글쓰기: 제목, 내용 작성 후 저장을 누르면 게시글이 저장되도록 처리

- 일반게시판 상세페이지: 제목, 작성날짜(수정날짜), 내용, 댓글 보이도록 처리, <br>
수정기능 - 게시글을 쓴 본인만 보이게 처리하고, 수정버튼을 누르면 수정페이지로 넘어가 본인이 쓴 내용을 수정할 수 있도록 처리, <br>
삭제기능 - 게시글을 쓴 본인만 보이게 처리하고, 삭제 버튼을 누르면 삭제여부 모달창을 띄우고 확인을 누르면 삭제완료 모달창이 뜨고 한번 더 확인을 누르면 삭제처리가 되어 목록으로 돌아감 하지만 취소 버튼을 누르면 그대로 상세페이지에 남도록 처리

- 일반게시판 댓글: 게시글 상세페이지에서 댓글을 추가 할 수 있고 내용을 작성 후 댓글달기 버튼을 누르면 작성자 작성날짜 내용이 표시되게 처리
</p>

<h3>🐬로그인 / 회원가입 / 아이디·비밀번호 찾기🐬</h3>
<p>

- 회원가입:
사용자가 필수 정보를 입력하면 새로운 사용자를 데이터베이스에 저장합니다. 
아이디와 닉네임은 중복확인이 필요합니다. 닉네임은 미입력시 아이디와 동일하게 저장됩니다. 비밀번호는 정규식을 통과해야만 저장하고, 통과하지 않으면 hidden 처리된 오류 메시지가 뜨게 합니다. 생년월일은 datepicker를 사용하여 선택할 수 있습니다. 이메일은 형식이 맞지 않으면 저장되지 않습니다. 이메일을 입력하고 인증번호받기 버튼을 누르면 인증번호를 입력할 수 있는 입력창이 띄워지고 인증번호가 일치하지 않으면 alert창이 띄워집니다. 핸드폰 번호는 숫자만 입력이 가능하고 실시간으로 hyphen(-)을 추가하여 XXX-XXXX-XXXX 형식으로 포맷팅되어 저장됩니다. 모든 필드가 올바르게 입력되고 모든 검증을 통과했을 시 가입하기 버튼이 활성화됩니다. 이미 같은 번호나 이메일로 가입한 이력이 있는 경우 저장되지 않습니다.


- 로그인: 
사용자가 아이디와 비밀번호 모두 입력했을 경우에만 로그인 버튼이 활성화됩니다. 아이디 저장 셀렉트 버튼을 선택하면 로그아웃 후에도 다시 로그인 브라우저를 열었을 때 전에 썼던 아이디가 자동으로 채워집니다. 탈퇴한 회원이나 없는 정보로 로그인을 시도할 경우 alert창을 띄워 로그인을 방지합니다. 


- 아이디 찾기:
아이디 찾기는 토글버튼을 이용하여 휴대전화 또는 이메일 인증을 통해 할 수 있습니다. 인증번호 받기 버튼을 누르면 인증번호를 입력할 수 있는 숨겨진 입력창이 띄워지고 인증번호가 일치하지 않으면 alert창이 띄워집니다. 인증번호가 일치하고 필요한 정보를 모두 입력하면 아이디 찾기 버튼이 활성화되고 모두 일치하는 정보일 시 아이디를 팝업 윈도우로 띄웁니다. 일치하지 않는 정보일 시 alert창을 띄웁니다. 팝업창의 확인버튼을 누르면 로그인 페이지로 이동합니다.


- 비밀번호 찾기: 
비밀번호 찾기도 마찬가지로 토글버튼을 이용하여 휴대전화 또는 이메일 인증을 통해 할 수 있습니다. 인증번호 받기 버튼을 누르면 인증번호를 입력할 수 있는 숨겨진 입력창이 띄워지고 인증번호가 일치하지 않으면 alert창이 띄워집니다. 인증번호가 일치하고 필요한 정보를 모두 입력하면 비밀번호 찾기 버튼이 활성화되고 모두 일치하는 정보일 시 비밀번호 변경 페이지로 이동합니다. 일치하지 않는 정보일 시 alert창을 띄웁니다. 비밀번호 변경 페이지에서 기존에 쓰던 비밀번호를 입력한 경우 alert창을 띄웁니다. 성공적으로 비밀번호를 변경한 경우 로그인 페이지로 이동합니다.
</p>


<h3>💖마이페이지💖</h3>
<p>
- 개인정보 조회:
  로그인한 회원의 아이디, 닉네임, 가입날짜등 개인 정보를 조회할 수 있고 사진과 닉네임을 변경할 수 있습니다.

- 비밀번호 수정:
  사용자가 마이페이지에서 비밀번호 수정 버튼을 누르면, 비밀번호를 변경하는 창으로 이동하며 이동한 후에는 기존 비밀번호와 새로운 비밀번호를 입력하라는 창이 뜹니다. 기존 비밀번호를 입력할 때는 데이터 베이스에 저장된 회원의 비밀번호와 일치하면 '비밀번호가 일치합니다', 일치하지 않으면 '비밀번호가 일치하지 않습니다'라는 글자가 뜹니다. 또한 기존 비밀번호를 입력한 후에 새로운 비밀번호를 입력할 때에는 새로운 비밀번호와 새로운 비밀번호 확인하는 비밀번호가 일치해야 비밀번호 변경 완료하는 버튼이 활성화 됩니다. 비밀번호를 변경한 후에는 다시 마이페이지로 이동하며 변경한 비밀번호는 로그인 창에서 확인 가능합니다.

- 회원 탈퇴:
  사용자가 마이페이지 안에서 회원 탈퇴 버튼을 누르면 진짜 탈퇴하시겠습니까? 라는 모달 창이 뜨고 회원이 다시 '예' 버튼을 클릭하면 로그인 창으로 돌아가며 데이터 베이스에서 해당 사용자의 상태가 Activited에서 Deleted로 변경됩니다.
  탈퇴한 사용자가 다시 로그인 창에서 로그인을 하면 알러트 창으로 탈퇴한 회원이라고 뜹니다.
</p>

<h2>🗣️ 후기</h2>

<p>권은혜: 프로젝트를 진행하면서 결과보다 과정의 소중함을 많이 느끼게 되었습니다. 개발을 할 때에도 사용자에게 보이는 홈페이지가 있으려면 그 뒤에 수많은 코딩 작업이 있어야 하는 것처럼, 팀 프로젝트에도 하나의 결과물을 만들어 내는 것에 수많은 과정이 있고, 그 과정의 소중함을 알고 과정을 차근차근 알아갈 때 좋은 결과를 낼 수 있다는 것을 깨닫게 되었습니다. 수업에서 배운 내용을 실제 프로젝트에 적용 해보면서 이론지식으로만 있었던 것들이 어떻게 실제 작동되고 구현되는지 확인할 수 있었고, 내가 필요한 기능을 찾아 적용해보면서 더 많은 지식을 활용하는 방법에 대해 알게 되었습니다. 팀장이기 이전에 한 명의 팀원으로 다른 팀원을 신뢰하고 협력하고 함께하는 것이 중요하다는 것을 다시 한 번 알게 되었습니다. 이번 프로젝트가 저에게는 단순한 결과물을 내는 그 이상의 가치가 있었다고 생각합니다.</p>
<br>

<p>홍주연: 팀 프로젝트를 마치고 나니 성취감과 뿌듯함을 느낍니다. 이론적으로만 알고 있던 개념들을 실제로 적용해 보면서 그 이해도가 훨씬 향상되었고, 단순히 코딩 기술만이 아니라, 협업의 중요성, 문제 해결 능력, 그리고 효과적인 일정 관리 등 여러 방면에서 성장할 수 있었습니다. 비록 짧은 기간 동안 진행된 프로젝트지만, 그 과정에서 팀원들과의 협력, 소통을 통해 많은 것을 배웠고 이러한 경험이 앞으로의 작업에도 중요한 밑바탕이 될 것이라 생각됩니다.</p>
<br>

<p>양혜연: 진행하면서 일이 많았어서 잘 될지 걱정이 많았습니다. 실제로 예상대로 풀리지 않은 부분도 있었고, 시간도 부족했고, 생각했던 기능들을 모두 구현하지는 못했습니다. 하지만 최소한의 필수 기능은 만들어 본것같다고 생각합니다. 또, 기능을 개발하는 과정에서 ‘이게 맞을까?’ 하는 생각도 많이 들었지만 의외로 잘된부분도 있어서 뿌듯했습니다. 그리고 생각보다 팀원들이랑 같이 작업하는게 쉬운게 아니라고 느꼈었습니다. 하지만 다들 열심히 한 덕분에 잘 마무리 한 것 같다고 생각합니다. 마지막으로 이번 음식게시판을 만들면서 배웠던것들을 직접 해보니 신기했고, 어렵다는것을 느꼈습니다. 그래서 앞으로 프로젝트를 이어가며 몰랐던 점이나 확실하지 않았던 부분들을 정확하게 다듬어가며 개선해보려고 합니다.</p>
<br>

<p>박효찬: 수업시간에 배운 것들을 가지고 이렇게 실제로 구현할 수 있던 것이 신기했고, 이렇게 협업을 통해 자신이 몰랐던 것을 팀원과 소통을 통해 배울수 있어서 좋았고, 이 시간을 통해 자신이 무엇이 부족했고, 막혔던 것도 이렇게 하면 해결할 수 있다는 문제해결 과정을 배울 수 있었다. 다음 프로젝트에서는 기존에 내가 부족했던 점들을 개선해서 만들어야 한다고 생각한다. </p>
<br>

<h3>감사합니다.</h3>
