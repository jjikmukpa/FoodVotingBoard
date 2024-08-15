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



<h2>🖱️ 기능명 | 💫기능내용</h2>
<h3>🎈논리게시판🎈</h3>
<p>논리게시판 | 페이징(내림차순)- 나중에 작성한글을 먼저 보이게 해서 정렬함, 
    검색- 단어를 검색해서 제목+내용이 있으면 보이게 함
     일반게시판 버튼 - 일반게시판으로 링크를 걸어서 누르면 일반게시판으로 이동함 
    게시판 - 번호, 제목, 작성자, 작성일, 조회수를 보여주고, 게시글을 누르면 그
                            게시글의 상세페이지로 이동
    조회수 - 게시글을 누르면 조회수가 증가한다.

논리게시판 (글쓰기) : 임시저장 - 자신이 작성한 제목, 내용, 저장시킨 사진을 임시저장해 다시 글을 쓸 때 섰던
    것을 가져올 수 있다.
               사진추가 - 사진을 추가 하면 미리보기를 통해 자신이 어떤 사진을 올렸는지 알 수 있고,
                  디렉토리에 사진을 저장하고 그 경로를 DB에 저장함                                     
    저장 - 자신이 글쓰기에서 작성한 글을 DB에 저장한다.
   목록 - 목록버튼을 누르면 논리 게시판으로 이동한다.

논리게시판(상세페이지) : 상세페이지 - 글쓰기에서 작성한 것(제목, 내용, 사진)을 상세페이지를 통해 보여준다.
       투표 기능 -  사진 둘중에 하나를 선택하면 '사진을 저장하시겠습니까?' 라는 창을 보여주고
                               확인을 누르면 클릭한 사진에 투표수가 증가한다. 
</p>
<h3>🎇일반게시판🎇</h3>
<p>
일반게시판 : 게시글을 보여주고 게시글을 누르면 상세페이지로 넘어가게 처리, <br>
            페이징처리 - 내림차순 정리. 최근순으로 정렬. 한 페이지당 게시글 10개만 보이게 처리, <br>
            검색 - 제목이나 내용을 검색하면 해당하는 게시글만 나오도록 처리, <br>
            논쟁게시판 버튼 - 누르면 논쟁게시판 페이지로 넘어가도록 처리

일반게시판 글쓰기 : 제목, 내용 작성 후 저장을 누르면 게시글이 저장되도록 처리

일반게시판 상세페이지 : 제목, 작성날짜(수정날짜), 내용, 댓글 보이도록 처리, <br>
수정기능 - 게시글을 쓴 본인만 보이게 처리하고, 수정버튼을 누르면 수정페이지로 넘어가 본인이 쓴 내용을 수정할 수 있도록 처리, <br>
삭제기능 - 게시글을 쓴 본인만 보이게 처리하고, 삭제 버튼을 누르면 삭제여부 모달창을 띄우고 확인을 누르면 삭제완료 모달창이 뜨고 한번 더 확인을 누르면 삭제처리가 되어 목록으로 돌아감 하지만 취소 버튼을 누르면 그대로 상세페이지에 남도록 처리

일반게시판 댓글 : 게시글 상세페이지에서 댓글을 추가 할 수 있고 내용을 작성 후 댓글달기 버튼을 누르면 작성자 작성날짜 내용이 표시되게 처리
</p>
      
  


<h2>🗣️ 후기</h2>
<p>권은혜: </p>
<p>홍주연: 팀 프로젝트를 마치고 나니 성취감과 뿌듯함을 느낍니다. 이론적으로만 알고 있던 개념들을 실제로 적용해 보면서 그 이해도가 훨씬 향상되었고, 단순히 코딩 기술만이 아니라, 협업의 중요성, 문제 해결 능력, 그리고 효과적인 일정 관리 등 여러 방면에서 성장할 수 있었습니다. 비록 짧은 기간 동안 진행된 프로젝트지만, 그 과정에서 팀원들과의 협력, 소통을 통해 많은 것을 배웠고 이러한 경험이 앞으로의 작업에도 중요한 밑바탕이 될 것이라 생각됩니다.</p>
<p>양혜연: 진행하면서 일이 많았어서 잘 될지 걱정이 많았습니다. 실제로 예상대로 풀리지 않은 부분도 있었고, 시간도 부족했고, 생각했던 기능들을 모두 구현하지는 못했습니다. 하지만 최소한의 필수 기능은 만들어 본것같다고 생각합니다. 또, 기능을 개발하는 과정에서 ‘이게 맞을까?’ 하는 생각도 많이 들었지만 의외로 잘된부분도 있어서 뿌듯했습니다. 그리고 생각보다 팀원들이랑 같이 작업하는게 쉬운게 아니라고 느꼈었습니다. 하지만 다들 열심히 한 덕분에 잘 마무리 한 것 같다고 생각합니다. 마지막으로 이번 음식게시판을 만들면서 배웠던것들을 직접 해보니 신기했고, 어렵다는것을 느꼈습니다. 그래서 앞으로 프로젝트를 이어가며 몰랐던 점이나 확실하지 않았던 부분들을 정확하게 다듬어가며 개선해보려고 합니다.</p>
<p>박효찬: 수업시간에 배운 것들을 가지고 이렇게 실제로 구현할 수 있던 것이 신기했고, <br>
    이렇게 협업을 통해 자신이 몰랐던 것을 팀원과 소통을 통해 배울수 있어서 좋았고, <br>
    이 시간을 통해 자신이 무엇이 부족했고, 막혔던 것도 이렇게 하면 해결할 수 있다는 <br>
    문제해결 과정을 배울 수 있었다. <br>
    다음 프로젝트에서는 기존에 내가 부족했던 점들을 개선해서 만들어야 한다고 생각한다.  </p>


<h3>감사합니다.</h3>
