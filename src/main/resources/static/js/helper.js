function checkArraysEquality(array1, array2) {
    if (!Array.isArray(array1) || !Array.isArray(array2)) return false;

    if (array1.length !== array2.length) return false;

    array1.forEach(function (value, index) {
        if (array1[index] !== array2[index]) return false;
    });

    return true;
}