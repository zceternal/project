function ToConvtZH(val)
{
    var sd = ToCH(val);
    var sd2 = sd;
    if (val.toString().length > 2 && (parseInt(val/10 % 10) == 1))
    {
    	var n=sd.lastIndexOf('十');
    	sd2 =sd.substr(0,n+1)+"一"+sd.substr(n+1,sd.length-n);
    }
    return sd2;
}

function ToCH(val)
{
    var si = val.toString();
    var sd = "";
    if (si.length == 1)     //個
    {
        sd += GetCH(val);
        return sd;
    }
    else if (si.length == 2)//十
    {
        if (si.substring(0, 1) == "1"){
            sd += "十";}
        else{
            sd += (GetCH(val / 10) + "十");}
        sd += ToCH(val % 10);
    }
    else if (si.length == 3)//百
    {
        sd += (GetCH(val / 100) + "百"); 
        if ((val % 100).toString().length < 2)
            sd += "零";
        sd += ToCH(val % 100);
    }
    else if (si.length == 4)//千
    {
        sd += (GetCH(val / 1000) + "千");
        if ((val % 1000).toString().length < 3)
            sd += "零";
        sd += ToCH(val % 1000);
    }
    else if (si.length == 5)//萬
    {
        sd += (GetCH(val / 10000) + "萬");
        if ((val % 10000).toString().length < 4)
            sd += "零";
        sd += ToCH(val % 10000);
    }

    return sd;
}

function GetCH(val)
{
    var sd = "";
    switch (parseInt(val))
    {
        case 1:
            sd = "一";
            break;
        case 2:
            sd = "二";
            break;
        case 3:
            sd = "三";
            break;
        case 4:
            sd = "四";
            break;
        case 5:
            sd = "五";
            break;
        case 6:
            sd = "六";
            break;
        case 7:
            sd = "七";
            break;
        case 8:
            sd = "八";
            break;
        case 9:
            sd = "九";
            break;
        default:
            break;
    }
    return sd;
}