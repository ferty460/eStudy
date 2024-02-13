function openTab(evt, tabName) {
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("theory-lesson-content");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("lesson-tools-item");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active-tools-item", "");
    }
    document.getElementById(tabName).style.display = "block";
    evt.currentTarget.className += " active-tools-item";
}

document.getElementsByClassName("lesson-tools-item")[0].click();