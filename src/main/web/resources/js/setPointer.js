function getRValue() {// получает радиус из инпута
    let rValue = parseFloat($("#form\\:R_field_input").attr("aria-valuenow"));
    if (isNaN(rValue))
        rValue = getRValueFromTable();
    return rValue;
}

function getRValueFromTable() {// радиус из таблицы
    let rValueFromTable;
    rValueFromTable = parseFloat($("tbody tr").last().find(">:nth-child(3)").text());
    if (isNaN(rValueFromTable))
        rValueFromTable = 1;
    return rValueFromTable;
}

function drawPointsFromTable() {// рисует точки из таблицы
    $("tbody tr").each(function () {
        let point = $(this);
        let x = parseFloat(point.find(">:first-child").text());
        let y = parseFloat(point.find(">:nth-child(2)").text());
        let r = parseFloat(point.find(">:nth-child(3)").text());
        let result = point.find(">:nth-child(4)").text().trim() === "Y";

        if (isNaN(x) || isNaN(y) || isNaN(r)) {
            return;
        }
        let color;
        color = result ? "green" : "red";

        // color = "green";


        function xValueForPoint(x) {// точки по пикселям из таблицы
            return (x / r * 2 * 60 + 150)
        }

        function yValueForPoint(y) {
            return (-y / r * 2 * 60 + 150)
        }

        let plot = $("svg")

        let existingPlot = plot.html()// задает размер и стиль точки клика
        let newPlot = `<circle id="pointer" r="5" cx="${xValueForPoint(x)}" cy="${yValueForPoint(y)}" fill-opacity="0.7" fill="${color}" stroke="firebrick" visibility="visible"></circle>`
        plot.html(existingPlot + newPlot)
    })
}


function getXFromSVG(x, r) {// рисует точки по пикселям
    return (x - 150) / 60 / 2 * r;
}

function getYFromSVG(y, r) {// рисует точки по пикселям
    return (y - 150) / 60 / 2 * -r;
}

function clickPlotHandler(e) {// рисует клики
    let offset = $(this).offset();
    let x = e.pageX - offset.left;
    let y = e.pageY - offset.top;

    let xValue = getXFromSVG(x, getRValue()).toFixed(2);
    let yValue = getYFromSVG(y, getRValue()).toFixed(2);

    $(".x-hidden").val(xValue);
    $(".y-hidden").val(yValue);
    $(".r-hidden").val(getRValue());
    $(".hidden-submit-btn").click();

}

$("svg").click(clickPlotHandler)// функция для рисовалки
drawPointsFromTable()


