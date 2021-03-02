function zero_first_format(value) {// для верного формата времени
    if (value < 10) {
        value = '0' + value;
    }
    return value;
}

function date_time() {// конкретно форматирует минуты секунды и тд подряд
    const current_datetime = new Date();
    const day = zero_first_format(current_datetime.getDate());
    const month = zero_first_format(current_datetime.getMonth() + 1);
    const year = current_datetime.getFullYear();
    const hours = zero_first_format(current_datetime.getHours());
    const minutes = zero_first_format(current_datetime.getMinutes());
    const seconds = zero_first_format(current_datetime.getSeconds());

    return "Дата "+ day + "." + month + "." + year + "<br/> Время " + hours + ":" + minutes + ":" + seconds;

}

setTimeout(function () { // периодичность обновления времени=ноль
    document.getElementById('current_date_time').innerHTML = date_time();
}, 0)

setInterval(function () {//периодичность 12 секунд
    document.getElementById('current_date_time').innerHTML = date_time();
}, 12000);