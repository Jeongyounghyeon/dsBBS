<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.net.URLDecoder"%>
<%@ page session="false" %>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Register</title>

    <!-- Custom fonts for this template-->
    <link href="${pageContext.request.contextPath}/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="${pageContext.request.contextPath}/css/sb-admin-2.min.css" rel="stylesheet">

</head>

<body class="bg-gradient-primary">

<script>
    let msg="${msg}";
    if(msg=="SAVE_ERR") alert("회원가입 실패");
</script>

    <div class="container">

        <div class="card o-hidden border-0 shadow-lg my-5">
            <div class="card-body p-0">
                <!-- Nested Row within Card Body -->
                <div class="row">
                    <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
                    <div class="col-lg-7">
                        <div class="p-5">
                            <div class="text-center">
                                <h1 class="h4 text-gray-900 mb-4">Create an Account!</h1>
                            </div>
                            <div id="msg" class="msg">
                                <c:if test="${not empty param.msg}">
                                    <i class="fa fa-exclamation-circle"> ${URLDecoder.decode(param.msg)}</i>
                                </c:if>
                            </div>
                            <form action="<c:url value="/register/save"/>" method="POST" class="user" onsubmit="return formCheck(this)">
                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                        <input type="text" class="form-control form-control-user" id="id" name="id"
                                            placeholder="id">
                                    </div>
                                    <div class="col-sm-6">
                                        <input type="password" class="form-control form-control-user" id="pwd" name="pwd"
                                            placeholder="Password">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <input type="email" class="form-control form-control-user" id="email" name="email"
                                        placeholder="Email Address">
                                </div>
                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                        <input type="text" class="form-control form-control-user" name="name"
                                            id="name" placeholder="Name">
                                    </div>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control form-control-user" name="pos" onblur="return posCheck(this)"
                                            id="pos" placeholder="position">
                                    </div>
                                </div>
                                <button class="btn btn-primary btn-user btn-block">
                                    Register Account
                                </button>
<%--                                <hr>--%>
<%--                                <a href="index.jsp" class="btn btn-google btn-user btn-block">--%>
<%--                                    <i class="fab fa-google fa-fw"></i> Register with Google--%>
<%--                                </a>--%>
<%--                                <a href="index.jsp" class="btn btn-facebook btn-user btn-block">--%>
<%--                                    <i class="fab fa-facebook-f fa-fw"></i> Register with Facebook--%>
<%--                                </a>--%>
                            </form>
                            <hr>
                            <div class="text-center">
                                <a class="small" href="forgot-password.html">Forgot Password?</a>
                            </div>
                            <div class="text-center">
                                <a class="small" href="login.jsp">Already have an account? Login!</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>

    <script>
        function formCheck(frm) {
            let msg ='';

            if(frm.id.value.length<3) {
                setMessage('id의 길이는 3이상이어야 합니다.', frm.id);
                return false;
            }
            if(frm.pwd.value.length<3) {
                setMessage('pwd의 길이는 3이상이어야 합니다.', frm.pwd);
                return false;
            }

            if(frm.pos.value!="학생" && frm.pos.value!="교수")
                return false;

            return true;
        }

        function setMessage(msg, element){
            document.getElementById("msg").innerHTML = `<i class="fa fa-exclamation-circle"> ${'${msg}'}</i>`;
            if(element) {
                element.select();
            }
        }

        function posCheck(pos) {
            if(pos.value!="학생" && pos.value!="교수")
                setMessage('position을 학생 또는 교수로 입력해주세요.');
        }
    </script>

    <!-- Bootstrap core JavaScript-->
    <script src="${pageContext.request.contextPath}/vendor/jquery/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="${pageContext.request.contextPath}/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="${pageContext.request.contextPath}/js/sb-admin-2.min.js"></script>

    <style>
        #msg {
            display: block;
            text-align: center!important;
        }
    </style>
</body>

</html>