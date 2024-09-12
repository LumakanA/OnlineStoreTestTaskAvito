# OnlineStoreTestTaskAvito

## Описание проекта

OnlineStoreTestTaskAvito — это тестовое Android-приложение, разработанное для стажировки в Avito. Оно демонстрирует умение разрабатывать приложения на платформе Android, используя современные подходы и принципы разработки. Приложение включает функционал регистрации и просмотра списка товаров.

## Технологии и архитектура

- **Kotlin**: Язык программирования, используемый для разработки приложения.
- **Чистая архитектура (Clean Architecture)**: Применяется для обеспечения гибкости и масштабируемости проекта.
- **MVVM**: Архитектурный шаблон для разделения логики приложения и представления.
- **Retrofit**: Библиотека для работы с сетевыми запросами.
- **Kotlin Coroutines**: Используются для асинхронного выполнения задач.
- **ViewModel**: Компонент архитектуры Android для управления UI-данными.
- **Jetpack Compose**: Современная библиотека для создания пользовательского интерфейса.
- **Koin**: Библиотека для внедрения зависимостей.
- **Jetpack Navigation**: Библиотека для управления навигацией в приложении.

## Инструкция по запуску

1. **Клонирование репозитория**

    ```bash
    git clone https://github.com/LumakanA/OnlineStoreTestTaskAvito.git
    ```

2. **Открытие проекта**

    Откройте проект в Android Studio.

3. **Настройка зависимостей**

    Убедитесь, что все зависимости указаны в файле `build.gradle`. Обновите зависимости и синхронизируйте проект с Gradle.

4. **Запуск приложения**

    Подключите Android-устройство или запустите эмулятор. Нажмите на кнопку "Run" в Android Studio или выполните команду:

    ```bash
    ./gradlew installDebug
    ```

## Проблемы и решения

1. **Невозможность входа без регистрации**

    - **Решение**: В техническом задании не указано об этом ограничении, поэтому оставлено как есть.

2. **Недостатки в интерфейсе (UI) из-за отсутствия макета**

    - **Решение**: Интерфейс разработан самостоятельно в связи с отсутствием макета в Figma или аналогичных инструментах и может отличаться от идеального дизайна. В будущем можно улучшить макеты.

3. **Меню отображается только на этапе логина**

    - **Решение**: Это сделано в соответствии с предоставленным макетом.

4. **Отсутствие фильтрации по цене**

    - **Решение**: Функциональность не была реализована в рамках текущего этапа разработки, но может быть добавлена в будущем.

5. **Отсутствие адекватной разметки экрана описания продукта**

    - **Решение**: Требуется дополнительная работа над улучшением разметки экрана описания продукта. В дальнейшем можно доработать его для лучшего отображения информации.

6. **Отсутствие кэширования запросов и офлайн-режима**

    - **Решение**: Эта функциональность не была реализована из-за ограничений времени. В будущем можно добавить возможность кэширования и работы в офлайн-режиме.

7. **Отсутствие пролистывания фотографий на экране описания продукта**

    - **Решение**: В текущей версии приложения фотографии продукта отображаются статично. Реализация возможности пролистывания фотографий требует дополнительного времени для разработки и тестирования. Этот функционал может быть добавлен в будущем.

8. **Мелкие недоработки и неточности6**

    - **Решение**: Возможно, были упущены некоторые детали или мелкие недоработки из-за ограниченного времени на выполнение задания. В будущем рекомендуется провести дополнительное тестирование и улучшение приложения.
