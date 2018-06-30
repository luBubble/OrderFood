var pathName = window.document.location.pathname;
var basePath = pathName.substring (0, pathName.substr (1).indexOf ('/') + 1);
// alert(basePath);



function checkFill ()
{
    var userId = user.userId.value;
    var password = user.password.value;
    
    var phoneRegular = /^1[3|4|5|7|8][0-9]{9}$/;
    var emailRegular = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
    
    if (userId.length != 0 && password.length != 0)
    {
        if (!phoneRegular.test(userId)&&!emailRegular.test(userId))
        {
	        alert ("账号格式有误，请重新输入");
	        return false;
        }
        return true;
    }
    else
    {
        alert ("请输入完整信息！");
        return false;
    }
}

function checkRegisterFill ()
{
    var userId = newUser.userId.value;	       
    var name = newUser.name.value;
    var password = newUser.password.value;
    var confirmPassword = newUser.confirmPassword.value;
    var userType=newUser.userType.value;
    
    var phoneRegular = /^1[3|4|5|7|8][0-9]{9}$/;
    var emailRegular = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
    
    if (userId.length != 0 &&name.length != 0 && password.length != 0 && confirmPassword.length != 0&&userType.length!=0)
    {
        if (!phoneRegular.test(userId)&&!emailRegular.test(userId))
        {
	        alert ("账号输入格式有误，请重新输入");
	        return false;
        }
        else if (password != confirmPassword)
        {
	        alert ("密码和确认密码不一致，请重新输入！");
	        return false;
        }
        return true;
    }
    else
    {
        alert ("请输入完整信息！");
        return false;
    }
}

