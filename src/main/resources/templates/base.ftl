<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
    <title></title>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/css/bootstrap.min.css" rel='stylesheet' type="text/css">
    <script type="text/javascript" src="/js/jquery.slim.min.js"></script>
    <script type="text/javascript" src="/js/popper.min.js"></script>
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>

</head>
<body>
<div class="container-fluid">

    <nav class="navbar navbar-expand-md bg-dark navbar-dark">
        <!-- Brand -->
        <a class="navbar-brand" href="#">文件暂存</a>

        <!-- Toggler/collapsibe Button -->
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>

        <!-- Navbar links -->
        <div class="collapse navbar-collapse" id="collapsibleNavbar">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="/index/">首页</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/qrcode/">二维码</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/list/">请求头</a>
                </li>
            <li class="nav-item">
            <a class="nav-link" href="/wiki/">wiki</a>
            </li>
                <li class="nav-item">
                    <a class="nav-link" href="/upload/">上传文件</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/file/">文件列表</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/ocr/">文字识别</a>
                </li>
            </ul>
        </div>
    </nav>
</div>
<div class="panel-body">
<@block name="body">base_body_content</@block>
</div>
<style type="text/css">
    .div_foot {
        height: 50px;
        text-align: center;
        position:fixed; bottom:0px; left:0px;
        line-height: 50px;
        width: 100%;}
</style>
</body>
<div class="div_foot"> 蜀ICP备2021025507号</div>

</html>