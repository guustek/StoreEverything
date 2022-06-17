const categorySelect = document.getElementById("category");
const newCategoryDiv = document.getElementById("new_category_div");
const newCategoryInput = document.getElementById("new_category_input");
const categorySelectDiv = document.getElementById("category_select_div")
const form = document.getElementById("form")

if (categorySelect.selectedIndex === categorySelect.options.length - 1) {
    showNewCategoryInput()
    let savedCategoryName = sessionStorage.getItem("new_category_name")
    newCategoryInput.value = savedCategoryName != null ? savedCategoryName : ""
    console.log(newCategoryInput.value)
    console.log(savedCategoryName)
} else
    newCategoryInput.value = categorySelect.options[categorySelect.selectedIndex].text

function showCategorySelect() {
    newCategoryDiv.disabled = true;
    newCategoryDiv.style.display = 'none';

    categorySelectDiv.disabled = false;
    categorySelectDiv.style.display = 'inline';
    categorySelect.focus()
    categorySelect.selectedIndex = 0
    newCategoryInput.value = categorySelect.options[0].text
}

function showNewCategoryInput() {
    categorySelectDiv.disabled = true;
    categorySelectDiv.style.display = 'none';


    newCategoryDiv.disabled = false;
    newCategoryDiv.style.display = 'inline';
    newCategoryInput.focus()
    newCategoryInput.value = ""
}

categorySelect.onchange = () => {
    if (categorySelect.options[categorySelect.selectedIndex].value === categorySelect.options[categorySelect.options.length - 1].value)
        showNewCategoryInput()
    else {
        newCategoryInput.value = categorySelect.options[categorySelect.selectedIndex].text
    }
}

newCategoryInput.onkeydown = (event) => {
    if (event.key === "Escape") {
        showCategorySelect()
    }
}

form.onsubmit = () => {
    sessionStorage.setItem("new_category_name", newCategoryInput.value)
    return true
}

