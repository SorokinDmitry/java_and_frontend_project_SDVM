Правила работы с этим репозиторием

1. В начале работы открываем консоль, переходим в рабочую директорию, 
  ПАПКУ ДЛЯ ПРОЕКТА НЕ СОЗДАЁМ, она будет создана автоматически! Делаем

  git clone АДРЕС ЭТОГО РЕПОЗИТОРИЯ
  
  Успех! У Вас на машине копия репозитория!!!
  
2. Перед тем, как что-то поменять в скачанных файлах, сделайте следующее:
 
  git status 

  // должно написать, что у вас ничего не изменилось, никаких modifyed, deleted!!!

  // если результат отличается, больше руками ничего не трогать, писать мне (Сергею)
  
  git checkout -b NAME\_OF\_YOUR\_NEW\_BRANCH 

  // вместо NAME\_... пишем название ветки, в котором должно

  // отражаться содержание того, что Вы хотите сделать
                                          
  git status 
  
  // убеждаемся, что всё ок, Вы в своей новой ветке.
  
3. Правите файлы, добавляете новые, делаете всё, что считаете нужным и полезным для проекта в СВОЕЙ ВЕТКЕ!!!

 Затем пишем
 
  git status 
  
  // выведется куча изменённых, добавленных и удалённых файлов
  
  git add . 
  
  // фиксируем изменения
  
  git status 
  
  // все изменения зелёные, всё хорошо
  
  git commit -m"Я исправил файл a.cpp, удалил b.h" 
  
  // в кавычках -- Ваш комментарий
  
4. Когда Вы Сделали всё, что хотели и довольны своей работой, считаете,
что Вы сделали всё возможное в этой ветке, делаете следующее
  
  git status

  // убеждаемся, что всё "up\_to\_date"

  git checkout master
  
  git pull origin master 
  
  // другие не спали, что - -то поменяли, нужно это скачать
  
  git status 
  
  // всё ок
  
  git merge NAME_OF_YOUR_NEW_BRANCH 
  
  // тут он будет совмещать результаты Ваших трудов с тем, что сделали другие
  
  // в результате он выдаст сообщение со списком файлов, которые нужно слить
  
  // вручную. Открываем их, смотрим, что он нам понадобавлял, правим.

  Во время слияния Вам предложат написать эссе по поводу того, что Вы видите в этом слиянии.
  Жмём Ctrl+X , если не хотите писать (там внизу список комманд, разберётесь).
  После того, как просмотрели все проблемные файлы, делаем коммит
  
  git commit -m "Я добавил функциональность и слил ветки!" 
  
  // лучше описать, что именно добавили
  
  git push origin master
  
  git status
  
5. Наслаждаемся победой, проверяем, всё ли работает, если нет,
   пишем мне, будем отменять, откатывать, восстанавливать)))
