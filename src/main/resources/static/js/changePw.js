document.addEventListener("DOMContentLoaded", function() {
    const changePwForm = document.getElementById("changePwForm");
    const nowPw = document.getElementById("nowPw");
    const afterPw = document.getElementById("afterPw");
    const afterPwC = document.getElementById("afterPwC");
    const saveBtn = document.getElementById("saveBtn");

    const nowPwError = document.getElementById("nowPwError");
    const afterPwError = document.getElementById("afterPwError");
    const afterPwInvalid = document.getElementById("afterPwInvalid");

    const validatePassword = (password) => {
        const regex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{10,}$/;
        return regex.test(password);
    };

    nowPw.addEventListener("input", function() {
        nowPwError.style.display = "none";
        afterPwError.style.display = "none";

        // 현재 비밀번호 검증
        fetch("/member/checkPassword", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({ nowPw: nowPw.value }),
        })
            .then(response => response.json())
            .then(data => {
                if (data.valid) {
                    nowPwError.style.display = "block";
                    nowPwError.style.color = "green";
                    nowPwError.textContent = "현재 비밀번호와 일치합니다.";
                } else {
                    nowPwError.style.display = "block";
                    nowPwError.style.color = "red";
                    nowPwError.textContent = "현재 비밀번호와 일치하지 않습니다.";
                }
            })
            .catch(error => {
                console.error("Error:", error);
            });
    });

    afterPw.addEventListener("input", function() {
        afterPwInvalid.style.display = validatePassword(afterPw.value) ? "none" : "block";
        afterPwError.style.display = nowPw.value === afterPw.value ? "block" : "none";
        validateForm();
    });

    afterPwC.addEventListener("input", function() {
        validateForm();
    });

    const validateForm = () => {
        const isValid = validatePassword(afterPw.value) &&
            nowPw.value !== afterPw.value &&
            afterPw.value === afterPwC.value;
        saveBtn.disabled = !isValid;
    };

    changePwForm.addEventListener("submit", function(event) {
        event.preventDefault();

        fetch("/member/checkPassword", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({ nowPw: nowPw.value }),
        })
            .then(response => response.json())
            .then(data => {
                if (data.valid) {
                    changePwForm.submit();
                } else {
                    nowPwError.style.display = "block";
                    nowPwError.style.color = "red";
                    nowPwError.textContent = "현재 비밀번호와 일치하지 않습니다.";
                }
            })
            .catch(error => {
                console.error("Error:", error);
            });
    });
});
