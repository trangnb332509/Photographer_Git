function renderHomePager(id, pageindex, totalpage, gap)
{
    //get container of id 
    var container = document.getElementById(id);
    var i;
    var begin = "<a href='home?pageindex=";
    //show "gap" pages before current page
    for (i = pageindex - gap; i < pageindex; i++)
    {
        if (i >= 1)
        {
            container.innerHTML += begin + i + "'>" + i + "</a>"
        }
    }
    //show current page
    container.innerHTML += "<span>" + pageindex + "</span>"
    //show "gap" pages after current page
    for (i = pageindex + 1; i <= pageindex + gap; i++)
    {
        if (i <= totalpage)
        {
            container.innerHTML += begin + i + "'>" + i + "</a>"
        }
    }
}
function renderImagePager(id, pageindex, galeryID, totalpage, gap)
{
    //get container of id 
    var container = document.getElementById(id);
    var i;
    var begin = "<a href='galery?galeryID=" + galeryID + "&pageindex=";

    //show "gap" pages before current page
    for (i = pageindex - gap; i < pageindex; i++)
    {
        if (i >= 1)
        {
            container.innerHTML += begin + i + "'>" + i + "</a>"
        }
    }
    //show current page
    container.innerHTML += "<span>" + pageindex + "</span>"
    //show "gap" pages after current page
    for (i = pageindex + 1; i <= pageindex + gap; i++)
    {
        if (i <= totalpage)
        {
            container.innerHTML += begin + i + "'>" + i + "</a>"
        }
    }

}


