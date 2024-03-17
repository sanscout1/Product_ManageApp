<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <title>List</title>
</head>
<body>

<div class="container-fluid">
    <div class="row">
        <!-- 기존의 <h1>Header</h1> -->
        <div class="row">
            <div class="col">
                <nav class="navbar navbar-expand-lg navbar-light bg-light">
                    <div class="container-fluid">
                        <a class="navbar-brand" href="#">Navbar</a>
                        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                                data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup"
                                aria-expanded="false" aria-label="Toggle navigation">
                            <span class="navbar-toggler-icon"></span>
                        </button>
                        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                            <div class="navbar-nav">
                                <a class="nav-link active" aria-current="page" href="#">Home</a>
                                <a class="nav-link" href="#">Features</a>
                                <a class="nav-link" href="#">Pricing</a>
                                <a class="nav-link disabled">Disabled</a>
                            </div>
                        </div>
                    </div>
                </nav>
            </div>
        </div>
        <!-- header end -->
        <!-- 기존의 <h1>Header</h1>끝 -->

        <div class="row content">
            <div class="col">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Search Name</h5>
                        <form action="/product/list" method="get">
                            <input type="hidden" name="size" value="${pageRequestDTO.size}">
                            <div class="mb-3">
                                <input type="text" name="keyword" class="form-control"
                                       value='<c:out value="${pageRequestDTO.keyword}"/>'>
                            </div>
                            <div class="input-group mb-3">
                                <div class="float-end">
                                    <button class="btn btn-primary" type="submit">Search</button>
                                    <button class="btn btn-info clearBtn" type="reset">Clear</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <%--form 형태의 name은 중요하다--%>
            </div>
        </div>

    <%--        수정필요--%>


        <div class="row content">
            <div class="col">
                <div class="card">
                    <div class="card-header">
                        Featured
                    </div>
                    <div class="card-body">
                        <h5 class="card-title">Special title treatment</h5>
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col">Pno</th>
                                <th scope="col">Name</th>
                                <th scope="col">Price</th>
                                <th scope="col">Amount</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${responseDTO.dtoList}" var="dto">
                                <tr>
                                    <th scope="row">
                                        <a href="/product/read?pno=${dto.pno}&${pageRequestDTO.link}"
                                           class="text-decoration-none" data-pno="${dto.pno}">
                                            <c:out value="${dto.pno}"/>
                                        </a>
                                    </th>
                                    <td><c:out value="${dto.name}"/></td>
                                    <td><c:out value="${dto.price}"/></td>
                                    <td><c:out value="${dto.amount}"/></td>
                                </tr>
                            </c:forEach>

                            </tbody>
                        </table>

                        </table>

<%--                        수정필요--%>
                        <div class="float-end">
                            <ul class="pagination flex-wrap">
                                <c:if test="${responseDTO.prev}">
                                    <li class="page-item">
                                        <a class="page-link" data-num="${responseDTO.start -1}">Previous</a>
                                    </li>
                                </c:if>

                                <c:forEach begin="${responseDTO.start}" end="${responseDTO.end}" var="num">
                                    <li class="page-item ${responseDTO.page == num? "active":""} ">
                                        <a class="page-link" data-num="${num}">${num}</a></li>
                                </c:forEach>

                                <c:if test="${responseDTO.next}">
                                    <li class="page-item">
                                        <a class="page-link" data-num="${responseDTO.end + 1}">Next</a>
                                    </li>
                                </c:if>
                            </ul>

                        </div>

                        <script>

                            // document.querySelector(".pagination").addEventListener("click", function (e) {
                            //     e.preventDefault()
                            //     e.stopPropagation()
                            //
                            //     const target = e.target
                            //
                            //
                            //     if (target.tagName !== 'A') {
                            //         return
                            //     }
                            //     const num = target.getAttribute("data-num")
                            //
                            //     self.location = `/product/list?page=\${num}` //백틱(` `)을 이용해서 템플릿 처리
                            // }, false)

                            document.querySelector(".pagination").addEventListener("click", function (e) {
                                e.preventDefault()
                                e.stopPropagation()

                                const target = e.target

                                if (target.tagName !== 'A') {
                                    return
                                }
                                const num = target.getAttribute("data-num")

                                const formObj = document.querySelector("form")

                                formObj.innerHTML += `<input type='hidden' name='page' value='\${num}'>`

                                formObj.submit();

                            }, false)
                            //  이 부분이 있어야 search 상태에서 페이지를 유지한다 , 안하면 search 전의 전체 상태 페이지로 간다
                            <%--폼 선택: document.querySelector("form")을 통해 문서의 첫 번째 <form> 요소를 선택합니다. --%>
                            <%--    여기서는 /product/list로의 GET 요청을 처리하는 폼입니다.--%>

                            <%--    입력 필드 추가: 선택된 페이지 번호(num)를 사용하여, 폼 내에 새로운 숨겨진 입력 필드를 동적으로 추가합니다.--%>
                            <%--    이 입력 필드의 name 속성은 'page'로 설정되며, value는 클릭된 페이지네이션 링크의 data-num 속성값입니다.--%>
                            <%--    올바른 문자열 보간을 사용하기 위해서는 value='${num}' 형식을 사용해야 합니다.--%>

                            <%--    폼 제출: formObj.submit();를 호출하여 수정된 폼을 서버로 제출합니다.--%>
                            document.querySelector(".clearBtn").addEventListener("click", function (e) {
                                e.preventDefault()
                                e.stopPropagation()

                                self.location = '/product/list'

                            }, false)


                        </script>

                    </div>

                </div>
            </div>
        </div>

    </div>
    <div class="row content">
    </div>
    <div class="row footer">
        <!--<h1>Footer</h1>-->

        <div class="row   fixed-bottom" style="z-index: -100">
            <footer class="py-1 my-1 ">
                <p class="text-center text-muted">Footer</p>
            </footer>
        </div>

    </div>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>

</body>
</html>